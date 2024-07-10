package de.hannes.datadogpoc.service;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class DatadogApiKeyValidation {

    private static final String DATADOG_API_KEY = "86f7a7d7523a3693001e8ba1c6d90906";
    private static final String DATADOG_API_VALIDATE_URL = "https://api.us5.datadoghq.com/api/v1/validate";

    public static boolean validateApiKey() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/java");
        headers.set("DD-API-KEY", DATADOG_API_KEY);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.exchange(DATADOG_API_VALIDATE_URL, HttpMethod.GET, entity, String.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}