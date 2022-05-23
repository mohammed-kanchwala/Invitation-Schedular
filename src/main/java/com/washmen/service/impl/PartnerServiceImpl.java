package com.washmen.service.impl;

import com.washmen.entity.Partner;
import com.washmen.model.OfficeResponse;
import com.washmen.model.PartnerResponse;
import com.washmen.repository.PartnerRepository;
import com.washmen.service.PartnerService;
import com.washmen.util.Unit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PartnerServiceImpl implements PartnerService {
    private final PartnerRepository partnerRepository;

    public PartnerServiceImpl(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    @Value("${source.lat}")
    double sourceLat;

    @Value("${source.lon}")
    double sourceLon;

    @Override
    public List<Partner> listAll() {
        return StreamSupport.stream(partnerRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public void saveAll(List<Partner> partners) {
        partnerRepository.saveAll(partners);
    }

    @Override
    public void save(Partner partner) {
        partnerRepository.save(partner);
    }

    @Override
    public List<PartnerResponse> fetchPartners(Double requestedDistance) {
        List<Partner> partners = listAll();
        List<PartnerResponse> resultList = new ArrayList<>();
        partners.forEach(partner -> partner.getOffices().forEach(office -> {
            String[] coordinates = office.getCoordinates().split(",");
            double officeLat = Double.parseDouble(coordinates[0]);
            double officeLon = Double.parseDouble(coordinates[1]);
            double calculatedDistance = distance(sourceLat, sourceLon, officeLat, officeLon, Unit.K);
            if (calculatedDistance < requestedDistance) {
                Optional<PartnerResponse> partnerResponse = resultList.stream()
                        .filter(x -> Objects.equals(x.getOrganization(), partner.getOrganization()))
                        .findFirst();
                OfficeResponse officeResponse = new OfficeResponse(office.getLocation(), office.getAddress());
                if (partnerResponse.isPresent()) {
                    List<OfficeResponse> officeResponses = partnerResponse.get().getOffices();
                    officeResponses.add(officeResponse);
                    partnerResponse.get().setOffices(officeResponses);
                } else {
                    List<OfficeResponse> officeResponses = new ArrayList<>();
                    officeResponses.add(officeResponse);
                    resultList.add(new PartnerResponse(partner.getOrganization(),
                            partner.getWillWorkRemotely(), partner.getWebsite(), officeResponses));
                }
            }
        }));

        resultList.sort(Comparator.comparing(PartnerResponse::getOrganization));

        return resultList;
    }


    private double distance(double lat1, double lon1, double lat2, double lon2, Unit desiredUnit) {
        double theta = lon1 - lon2;
        double distance = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        distance = Math.acos(distance);
        distance = rad2deg(distance);
        distance = distance * 60 * 1.1515;
        if (desiredUnit.unitChar == Unit.K.unitChar) {
            distance = distance * 1.609344;
        } else if (desiredUnit.unitChar == Unit.NM.unitChar) {
            distance = distance * 0.8684;
        }
        return (distance);
    }

    private double deg2rad(double degree) {
        return (degree * Math.PI / 180.0);
    }

    private double rad2deg(double radian) {
        return (radian * 180.0 / Math.PI);
    }
}
