package com.project.ServiceBooking.data;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idaccount", nullable = false)
    private Integer id;

    @Column(name = "money")
    private Integer money;

    @Column(name = "card_number", length = 45)
    private String cardNumber;

    @OneToMany(mappedBy = "accountIdaccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<User> users = new LinkedHashSet<>();

}