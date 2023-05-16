package com.project.ServiceBooking.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "language")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlanguage")
    private Integer id;

    @Column(name = "language")
    private String language;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_id_users")
    private User userLang;
}
