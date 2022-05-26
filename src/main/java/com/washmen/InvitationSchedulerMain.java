package com.washmen;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.washmen.entity.Partner;
import com.washmen.service.PartnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
@Slf4j
public class InvitationSchedulerMain {

    @Value("${partners.file.path}")
    private String partnersFilePath;
    @Value("${api.partner.endpoint}")
    private String apiPartnerEndpoint;
    @Value("${ui.url.path}")
    private String uiURL;

    public static final String API_PARTNERS_ENDPOINT = "/api/partners/*";
    public static final String UI_URL = "http://localhost:3000";

    public static void main(String[] args) {
        SpringApplication.run(InvitationSchedulerMain.class);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping(apiPartnerEndpoint).allowedOrigins(uiURL);
            }
        };
    }
    @Bean
    CommandLineRunner runner(PartnerService partnerService) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Partner>> typeReference = new TypeReference<List<Partner>>() {
            };
            InputStream inputStream = TypeReference.class.getResourceAsStream(partnersFilePath);
            try {
                List<Partner> partners = mapper.readValue(inputStream, typeReference);
                partnerService.saveAll(partners);
                log.info("Partners Saved !!");
            } catch (IOException e) {
                log.error("Unable to save Partners ->" + e.getMessage());
            }
        };
    }
}