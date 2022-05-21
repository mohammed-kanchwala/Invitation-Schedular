package com.washmen.controller;

import com.washmen.service.PartnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class PartnerController {

    private final PartnerService partnerService;

    @Autowired
    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @GetMapping("/partners")
    public ResponseEntity<?> listAll() {
        log.info("PartnerController:  list partners");
        return ResponseEntity.ok(partnerService.listAll());
    }

    @GetMapping("/search")
    public ResponseEntity<?> fetchPartners(@RequestParam double distance) {
        log.info("PartnerController:  fetch partners");
        return ResponseEntity.ok(partnerService.fetchPartners(distance));
    }
}
