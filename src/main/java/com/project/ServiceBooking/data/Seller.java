package com.project.ServiceBooking.data;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "seller")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seller", nullable = false)
    private Integer id;

    @Column(name = "Skills", length = 1000)
    private String skills;

    @Column(name = "Education", length = 1000)
    private String education;

    @OneToMany(mappedBy = "sellerIdSeller")
    private Set<User> users = new LinkedHashSet<>();

}