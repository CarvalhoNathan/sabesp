package br.gov.sp.santos.semam.saneamento.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Integração com Supabase Storage via REST API para armazenamento das fotos em produção.
 */
@Service
@Profile("supabase")
public class SupabaseFotoStorageService implements FotoStorageService {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final String supabaseUrl;
    private final String serviceKey;
    private final String bucket;

    public SupabaseFotoStorageService(
            @Value("${supabase.url}") String supabaseUrl,
            @Value("${supabase.service-key}") String serviceKey,
            @Value("${supabase.storage.bucket:chamados-fotos}") String bucket) {
        this.supabaseUrl = supabaseUrl.replaceAll("/+$", "");
        this.serviceKey = serviceKey;
        this.bucket = bucket;
    }

    @Override
    public String salvarFoto(String protocolo, MultipartFile arquivo) {
        String extensao = extrairExtensao(arquivo.getOriginalFilename());
        String caminhoObjeto = protocolo + "/" + UUID.randomUUID() + extensao;
        String endpoint = supabaseUrl + "/storage/v1/object/" + bucket + "/" + caminhoObjeto;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endpoint))
                    .header("Authorization", "Bearer " + serviceKey)
                    .header("apikey", serviceKey)
                    .header("Content-Type", arquivo.getContentType() != null ? arquivo.getContentType() : "application/octet-stream")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(arquivo.getBytes()))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new IllegalStateException("Falha ao enviar foto para o Supabase Storage (HTTP " + response.statusCode() + ").");
            }

            return supabaseUrl + "/storage/v1/object/public/" + bucket + "/" + caminhoObjeto;
        } catch (IOException | InterruptedException e) {
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            throw new IllegalStateException("Erro de comunicação com o Supabase Storage.", e);
        }
    }

    private String extrairExtensao(String nomeOriginal) {
        if (nomeOriginal == null || !nomeOriginal.contains(".")) {
            return ".jpg";
        }
        return nomeOriginal.substring(nomeOriginal.lastIndexOf('.')).toLowerCase();
    }
}
