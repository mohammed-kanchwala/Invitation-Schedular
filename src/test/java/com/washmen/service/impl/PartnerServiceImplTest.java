package com.washmen.service.impl;

import com.washmen.entity.Partner;
import com.washmen.model.PartnerResponse;
import com.washmen.service.PartnerService;
import com.washmen.util.ErrorConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class PartnerServiceImplTest {

    private final PartnerService partnerService;

    @Autowired
    public PartnerServiceImplTest(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @Test
    @DisplayName("Fetch All Partners Test")
    public void testFindAll() {
        final List<Partner> result = partnerService.listAll();
        Assertions.assertEquals(17, result.size());
    }

    @Test
    @DisplayName("Fetch Partners with 50.0 km Distance Test")
    public void testFetchPartnersWith50kmDistance() {
        final List<PartnerResponse> result = partnerService.fetchPartners(50.0);
        Assertions.assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Fetch Partners with 0 km Distance Test")
    public void testFetchPartnersWith0kmDistance() {
        final List<PartnerResponse> result = partnerService.fetchPartners(0.0);
        Assertions.assertEquals(0, result.size());
    }

}
