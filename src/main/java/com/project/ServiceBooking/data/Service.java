package com.project.ServiceBooking.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_services", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_services_category")
    private ServicesCategory idServicesCategory;

    @Column(name = "description", length = 45)
    private String description;

    @Column(name = "price")
    private Float price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "users_id_users", nullable = false)
    private User usersIdUsers;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "avgrating")
    private Float avgrating;

    @Column(name = "countreview")
    private Integer countreview;

    @OneToMany(mappedBy = "servicesIdServices")
    private Set<Payment> payments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idServices")
    private Set<Review> reviews = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idServices")
    private Set<Booking> bookings = new LinkedHashSet<>();

}