package com.washmen;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.washmen.entity.Partner;
import com.washmen.service.PartnerService;
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
public class InvitationSchedulerMain {
    public static void main(String[] args) {
        SpringApplication.run(InvitationSchedulerMain.class);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/*").allowedOrigins("http://localhost:3000");
            }
        };
    }

    @Bean
    CommandLineRunner runner(PartnerService partnerService) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Partner>> typeReference = new TypeReference<List<Partner>>() {
            };
            InputStream inputStream = TypeReference.class.getResourceAsStream("/partners/partners.json");
            try {
                List<Partner> partners = mapper.readValue(inputStream, typeReference);
                partnerService.saveAll(partners);
                System.out.println("Partners Saved !!");
            } catch (IOException e) {
                System.err.println("Unable to save Partners ->" + e.getMessage());
            }
        };
    }
}