package br.gov.sp.santos.semam.saneamento.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Armazenamento local em disco para testes antes de conectar o Supabase Storage.
 */
@Service
@Profile("!supabase")
public class LocalFotoStorageService implements FotoStorageService {

    private static final Path UPLOAD_ROOT = Paths.get("uploads");

    @Override
    public String salvarFoto(String protocolo, MultipartFile arquivo) {
        try {
            Path destinoDir = UPLOAD_ROOT.resolve(protocolo);
            Files.createDirectories(destinoDir);
            String extensao = extrairExtensao(arquivo.getOriginalFilename());
            String nomeArquivo = UUID.randomUUID() + extensao;
            Path destino = destinoDir.resolve(nomeArquivo);
            Files.copy(arquivo.getInputStream(), destino);
            return "/uploads/" + protocolo + "/" + nomeArquivo;
        } catch (IOException e) {
            throw new IllegalStateException("Não foi possível salvar a foto enviada.", e);
        }
    }

    private String extrairExtensao(String nomeOriginal) {
        if (nomeOriginal == null || !nomeOriginal.contains(".")) {
            return ".jpg";
        }
        return nomeOriginal.substring(nomeOriginal.lastIndexOf('.')).toLowerCase();
    }
}
