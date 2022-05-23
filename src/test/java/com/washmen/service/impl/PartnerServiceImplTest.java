package com.washmen.service.impl;

import com.washmen.entity.Partner;
import com.washmen.model.PartnerResponse;
import com.washmen.service.PartnerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PartnerServiceImplTest {

    private final PartnerService partnerService;

    @Autowired
    public PartnerServiceImplTest(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @Test
    @DisplayName("Fetch All Partners Test")
    void testFindAll() {
        final List<Partner> result = partnerService.listAll();
        Assertions.assertEquals(17, result.size());
    }

    @Test
    @DisplayName("Fetch Partners within 50.0 km Distance Test")
    void testFetchPartners_Within50kmDistance() {
        final List<PartnerResponse> result = partnerService.fetchPartners(50.0);
        Assertions.assertEquals(2, result.size());
    }
    @Test
    @DisplayName("Fetch Partners within 100.0 km Distance Test")
    void testFetchPartners_Within100kmDistance() {
        final List<PartnerResponse> result = partnerService.fetchPartners(100.00);
        Assertions.assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Fetch Partners within 0 km Distance Test")
    void testFetchPartners_Within0kmDistance() {
        final List<PartnerResponse> result = partnerService.fetchPartners(0.0);
        Assertions.assertEquals(0, result.size());
    }

}
