package com.project.ServiceBooking.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_review", nullable = false)
    private Integer id;

    @Column(name = "Rating")
    private Integer rating;

    @Column(name = "Comment", length = 45)
    private String comment;

    @Column(name = "time_review", length = 45)
    private LocalDateTime time_review;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_services")
    private Service idServices;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "users_id_users", nullable = false)
    private User usersIdUsers;

}