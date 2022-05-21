package com.washmen.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Embeddable
@Entity
@Table(name = "Offices")
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String location;
    private String address;
    private String coordinates;
}