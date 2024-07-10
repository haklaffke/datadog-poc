package de.hannes.datadogpoc.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DatadogStartupRunner implements CommandLineRunner {

    @EventListener
    @Override
    public void run(String... args) throws Exception {
        DatadogApiKeyValidation.validateApiKey();
    }
}