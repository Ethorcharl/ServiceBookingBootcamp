package com.project.ServiceBooking.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Payment", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_users")
    private User idUsers;

    @Column(name = "id_seller")
    private Integer idSeller;

    @Column(name = "sum")
    private Integer sum;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "services_id_services", nullable = false)
    private Service servicesIdServices;

}