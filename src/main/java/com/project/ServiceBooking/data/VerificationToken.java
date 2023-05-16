package com.project.ServiceBooking.data;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "verification_token")
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_verification_token")
    private Integer tokenId;

    @Column(name = "token")
    private String token;

    @Column(name = "expiry_date")
    private Timestamp expiryDate;

    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "user_id", referencedColumnName = "id_users")
    private User user;

    public VerificationToken() {
    }

    public VerificationToken(User user, String token) {
        this.token = token;
        this.user = user;
    }

}
