package com.project.ServiceBooking.repositories;

import com.project.ServiceBooking.data.User;
import com.project.ServiceBooking.data.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);


}