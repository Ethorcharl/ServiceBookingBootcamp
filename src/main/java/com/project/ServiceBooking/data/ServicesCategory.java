package com.project.ServiceBooking.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "services_category")
public class ServicesCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_services_category", nullable = false)
    private Integer id;

    @Column(name = "Category", nullable = false, length = 45)
    private String category;

    @Column(name = "Sub_category", nullable = false, length = 45)
    private String subCategory;

    @OneToMany(mappedBy = "idServicesCategory")
    private Set<Service> services = new LinkedHashSet<>();

}