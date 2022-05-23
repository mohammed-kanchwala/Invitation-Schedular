package com.washmen.controller;

import com.washmen.entity.Partner;
import com.washmen.model.PartnerResponse;
import com.washmen.service.PartnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
@Validated
public class PartnerController {

    private final PartnerService partnerService;

    @Autowired
    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @GetMapping("/partners")
    public ResponseEntity<List<Partner>> listAll() {
        log.info("PartnerController:  list partners");
        return ResponseEntity.ok(partnerService.listAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<PartnerResponse>> fetchPartners(@Valid @RequestParam @Positive
                                                                   @Min(value = 0L, message = "value of distance must be positive") Double distance) {
        log.info("PartnerController:  fetch partners");
        return ResponseEntity.ok(partnerService.fetchPartners(distance));
    }
}
