package com.washmen.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Partners")
@Builder
public class Partner {
    @Id
    private Long id;
    private String urlName;
    private String organization;
    private String customerLocations;
    private Boolean willWorkRemotely;
    private String website;
    @Column(length = 1000)
    private String services;
    @OneToMany(targetEntity = Office.class, fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "partner_id")
    private List<Office> offices;

}