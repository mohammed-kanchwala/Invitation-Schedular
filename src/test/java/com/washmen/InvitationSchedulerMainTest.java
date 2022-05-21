package com.washmen;

import com.washmen.entity.Partner;
import com.washmen.repository.PartnerRepository;
import com.washmen.service.PartnerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InvitationSchedulerMainTest {
    private final PartnerService partnerService;
    private final PartnerRepository partnerRepository;


    @Autowired
    public InvitationSchedulerMainTest(PartnerService partnerService, PartnerRepository partnerRepository) {
        this.partnerService = partnerService;
        this.partnerRepository = partnerRepository;

    }

    @Test
    public void contextLoads() {
        Assertions.assertNotNull(partnerService);
        Assertions.assertNotNull(partnerRepository);
    }
}
