package com.washmen;

import com.washmen.repository.PartnerRepository;
import com.washmen.service.PartnerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InvitationSchedulerMainTest {
    private final PartnerService partnerService;
    private final PartnerRepository partnerRepository;


    @Autowired
    public InvitationSchedulerMainTest(PartnerService partnerService, PartnerRepository partnerRepository) {
        this.partnerService = partnerService;
        this.partnerRepository = partnerRepository;

    }

    @Test
    void contextLoads() {
        Assertions.assertNotNull(partnerService);
        Assertions.assertNotNull(partnerRepository);
    }
}
