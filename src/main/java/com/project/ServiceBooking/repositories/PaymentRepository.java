package com.project.ServiceBooking.repositories;

import com.project.ServiceBooking.data.Account;
import com.project.ServiceBooking.data.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
