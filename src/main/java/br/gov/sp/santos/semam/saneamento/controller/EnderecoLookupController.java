package br.gov.sp.santos.semam.saneamento.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Proxy leve no backend para consulta de CEP (ViaCEP) e autocomplete de endereços em Santos (Nominatim),
 * evitando bloqueios de CORS ou User-Agent no navegador.
 */
@RestController
@RequestMapping("/api")
public class EnderecoLookupController {

    private final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();

    @GetMapping(value = "/cep/{cep}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> buscarCep(@PathVariable String cep) {
        String digits = cep.replaceAll("\\D", "");
        if (digits.length() != 8) {
            return ResponseEntity.badRequest().body("{\"erro\": true}");
        }
        return forwardGet("https://viacep.com.br/ws/" + digits + "/json/");
    }

    @GetMapping(value = "/enderecos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> autocompletarEndereco(@RequestParam String q) {
        if (q == null || q.trim().length() < 3) {
            return ResponseEntity.ok("[]");
        }
        String termo = URLEncoder.encode(q.trim() + ", Santos, SP, Brasil", StandardCharsets.UTF_8);
        String url = "https://nominatim.openstreetmap.org/search?format=jsonv2&addressdetails=1&countrycodes=br&limit=5&accept-language=pt-BR&q=" + termo;
        return forwardGet(url);
    }

    private ResponseEntity<String> forwardGet(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(6))
                    .header("User-Agent", "SantosSaneamento-SEMAM/1.0")
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                return ResponseEntity.ok(response.body());
            }
            return ResponseEntity.status(response.statusCode()).body("[]");
        } catch (IOException | InterruptedException e) {
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            return ResponseEntity.internalServerError().body("{\"erro\": true}");
        }
    }
}
