package io.zephyr.ai.zephyraiservice.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;

@Service
public class DifyService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_KEY = "app-bWg0GqeLzoCoV14OO0Q5v95s";

    private final String WORKFLOW_URL =
            "https://api.dify.ai/v1/workflows/83d802ae-894e-41fb-9028-d1214736ba56/run";

    private final WebClient webClient = WebClient.builder().build();

    public Flux<String> runWorkflowStream(String message) {

        Map<String, Object> inputs = new HashMap<>();
        inputs.put("keywords", message);

        Map<String, Object> body = new HashMap<>();
        body.put("inputs", inputs);
        body.put("response_mode", "streaming");
        body.put("user", "spring-user");

        return webClient.post()
                .uri(WORKFLOW_URL)
                .header("Authorization", "Bearer " + API_KEY)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToFlux(String.class);
    }
}
