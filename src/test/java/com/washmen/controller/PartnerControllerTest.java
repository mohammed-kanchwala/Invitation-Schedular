package com.washmen.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.washmen.entity.Partner;
import com.washmen.util.ApiConstants;
import com.washmen.util.Utility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PartnerControllerTest {

    private final TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;


    @Autowired
    public PartnerControllerTest(TestRestTemplate restTemplate, ObjectMapper mapper) {
        this.restTemplate = restTemplate;
    }

    @Test
    @DisplayName("Test Fetching All Partners")
    void testFindAll() throws Exception {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Utility.createURL(port, "/"));
        ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Partner> partnerList = Utility.fetchPartnersFromResponse(response.getBody());
        assertNotNull(partnerList);
        assertEquals(17, partnerList.size());
    }

    @Test
    @DisplayName("Test Fetching Partners Within 50.0 km Distance")
    void testFetchPartners_Within50kmDistance() throws Exception {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put(ApiConstants.DISTANCE, "50.0");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Utility.createURL(port, ApiConstants.SEARCH));
        Utility.addQueryParametersToBuilder(queryParams, builder);
        ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Partner> partnerList = Utility.fetchPartnersFromResponse(response.getBody());
        assertNotNull(partnerList);
        assertEquals(2, partnerList.size());
    }

    @Test
    @DisplayName("Test Fetching Partners Within 0 km Distance")
    void testFetchPartners_Within0kmDistance() throws Exception {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put(ApiConstants.DISTANCE, "0");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Utility.createURL(port, ApiConstants.SEARCH));
        Utility.addQueryParametersToBuilder(queryParams, builder);
        ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @DisplayName("Test Fetching Partners Within 0.1 km Distance")
    void testFetchPartners_Within0Point1kmDistance() throws Exception {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put(ApiConstants.DISTANCE, "0.1");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Utility.createURL(port, ApiConstants.SEARCH));
        Utility.addQueryParametersToBuilder(queryParams, builder);
        ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Partner> partnerList = Utility.fetchPartnersFromResponse(response.getBody());
        assertNotNull(partnerList);
        assertTrue(partnerList.isEmpty());
    }

    @Test
    @DisplayName("Test Fetching Partners Within 100.00 km Distance")
    void testFetchPartners_Within100kmDistance() throws Exception {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put(ApiConstants.DISTANCE, "100");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Utility.createURL(port, ApiConstants.SEARCH));
        Utility.addQueryParametersToBuilder(queryParams, builder);
        ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Partner> partnerList = Utility.fetchPartnersFromResponse(response.getBody());
        assertNotNull(partnerList);
        assertEquals(2, partnerList.size());
    }

    @Test
    @DisplayName("Test Fetching Partners With Invalid or Bad Request Param")
    void testFetchPartners_WithInvalidRequestParam() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("distances", "0");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Utility.createURL(port, ApiConstants.SEARCH));
        Utility.addQueryParametersToBuilder(queryParams, builder);
        ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @DisplayName("Test Fetching Partners Within -5.0 km Distance")
    void testFetchPartners_WithinNegative5kmDistance() throws Exception {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put(ApiConstants.DISTANCE, "-5");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Utility.createURL(port, ApiConstants.SEARCH));
        Utility.addQueryParametersToBuilder(queryParams, builder);
        ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(Objects.requireNonNull(response.getBody()).contains("distance must be positive"));
    }

}
