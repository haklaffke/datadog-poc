package de.hannes.datadogpoc.service;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DatadogLogger {
    private static final String DATADOG_API_URL = "https://http-intake.logs.us5.datadoghq.com/api/v2/logs";
    private static final String DATADOG_API_KEY = "86f7a7d7523a3693001e8ba1c6d90906";
    private static final String DATADOG_APPLICATION_KEY = "fe882e7968f98e9da01db878bdd2aa46c03800fc";

    public static void sendLogToDatadog(Object logObject) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonLog = objectMapper.writeValueAsString(logObject);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("DD-SOURCE", "java");
            headers.set("DD-SERVICE", "datadog-poc");
            headers.set("DD-API-KEY", DATADOG_API_KEY);
            headers.set("Content-Type", "application/json");
            headers.set("Accept", "application/json");
            headers.set("DD-APPLICATION-KEY", DATADOG_APPLICATION_KEY);

            HttpEntity<String> request = new HttpEntity<>(jsonLog, headers);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject(DATADOG_API_URL, request, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions or errors here
        }
    }
}