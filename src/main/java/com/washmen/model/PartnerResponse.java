package com.washmen.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class PartnerResponse {
    private String organization;
    private Boolean willWorkRemotely;
    private String customerLocations;
    private String website;
    private List<OfficeResponse> offices;

}
