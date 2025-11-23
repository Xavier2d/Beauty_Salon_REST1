package ws.beauty.salon.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Map;

@Service
public class CognitiveService {

    @Value("${azure.cognitive.key}")
    private String apiKey;

    @Value("${azure.cognitive.endpoint}")
    private String endpoint;

    private final RestTemplate restTemplate = new RestTemplate();

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Ocp-Apim-Subscription-Key", apiKey);
        return headers;
    }

    // =========================================================
    // 🔹 ANÁLISIS DE SENTIMIENTO
    // =========================================================
    @SuppressWarnings("unchecked")
    public String analyzeSentiment(String text) {

        String url = endpoint + "language/:analyze-text?api-version=2025-11-01";

        Map<String, Object> document = Map.of(
                "id", "1",
                "language", "es",
                "text", text);

        Map<String, Object> body = Map.of(
                "kind", "SentimentAnalysis",
                "parameters", Map.of("modelVersion", "latest"),
                "analysisInput", Map.of("documents", List.of(document)));

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, getHeaders());

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);
        if (response.getBody() == null)
            return "unknown";

        @SuppressWarnings({ "rawtypes", "null" })
        Map results = (Map) response.getBody().get("results");
        if (results == null)
            return "unknown";

        @SuppressWarnings("rawtypes")
        List documents = (List) results.get("documents");
        if (documents == null || documents.isEmpty())
            return "unknown";

        @SuppressWarnings("rawtypes")
        Map documentResult = (Map) documents.get(0);

        return (String) documentResult.getOrDefault("sentiment", "unknown");
    }

    // =========================================================
    // 🔹 EXTRACCIÓN DE FRASES CLAVE
    // =========================================================
    @SuppressWarnings({ "null", "unchecked" })
    public List<String> extractKeyPhrases(String text) {

        String url = endpoint + "language/:analyze-text?api-version=2025-11-01";

        Map<String, Object> document = Map.of(
                "id", "1",
                "language", "es",
                "text", text);

        Map<String, Object> body = Map.of(
                "kind", "KeyPhraseExtraction",
                "parameters", Map.of("modelVersion", "latest"),
                "analysisInput", Map.of("documents", List.of(document)));

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, getHeaders());

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);
        if (response.getBody() == null)
            return List.of();

        @SuppressWarnings("rawtypes")
        Map results = (Map) response.getBody().get("results");
        @SuppressWarnings("rawtypes")
        List documents = (List) results.get("documents");
        @SuppressWarnings("rawtypes")
        Map documentResult = (Map) documents.get(0);

        return (List<String>) documentResult.getOrDefault("keyPhrases", List.of());
    }
}
