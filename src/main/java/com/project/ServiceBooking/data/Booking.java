package com.project.ServiceBooking.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Booking", nullable = false)
    private Integer id;

    @Column(name = "id_seller")
    private Integer idSeller;

    @Column(name = "Date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_users")
    private User idUsers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_services")
    private Service idServices;

}