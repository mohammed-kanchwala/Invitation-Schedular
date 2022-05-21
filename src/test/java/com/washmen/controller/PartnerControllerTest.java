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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PartnerControllerTest {

    private final TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;


    @Autowired
    public PartnerControllerTest(TestRestTemplate restTemplate, ObjectMapper mapper) {
        this.restTemplate = restTemplate;
    }

    @Test
    @DisplayName("Test Fetching All Partners")
    public void testFindAll() throws Exception {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Utility.createURL(port, ApiConstants.PARTNERS));
        ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Partner> partnerList = Utility.fetchPartnersFromResponse(response.getBody());
        assertNotNull(partnerList);
        assertEquals(17, partnerList.size());
    }

    @Test
    @DisplayName("Test Fetching Partners With 50.0 km Distance")
    public void testFetchPartnersWith50kmDistance() throws Exception {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("distance", "50.0");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Utility.createURL(port, ApiConstants.SEARCH));
        Utility.addQueryParametersToBuilder(queryParams, builder);
        ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Partner> partnerList = Utility.fetchPartnersFromResponse(response.getBody());
        assertNotNull(partnerList);
        assertEquals(2, partnerList.size());
    }

    @Test
    @DisplayName("Test Fetching Partners With 0 km Distance")
    public void testFetchPartners_With0kmDistance() throws Exception {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("distance", "0");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Utility.createURL(port, ApiConstants.SEARCH));
        Utility.addQueryParametersToBuilder(queryParams, builder);
        ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Partner> partnerList = Utility.fetchPartnersFromResponse(response.getBody());
        assertNotNull(partnerList);
        assertTrue(partnerList.isEmpty());
    }

    @Test
    @DisplayName("Test Fetching Partners With Invalid or Bad Request Param")
    public void testFetchPartners_WithInvalidRequestParam() throws Exception {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("distances", "0");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Utility.createURL(port, ApiConstants.SEARCH));
        Utility.addQueryParametersToBuilder(queryParams, builder);
        ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
