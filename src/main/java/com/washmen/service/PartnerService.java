package com.washmen.service;

import com.washmen.entity.Partner;
import com.washmen.model.PartnerResponse;

import java.util.List;

public interface PartnerService {
    List<Partner> listAll();

    void saveAll(List<Partner> partners);

    void save(Partner partner);

    List<PartnerResponse> fetchPartners(Double distance);
}
