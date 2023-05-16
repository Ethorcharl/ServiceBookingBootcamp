package com.project.ServiceBooking.repositories;

import com.project.ServiceBooking.data.Account;
import com.project.ServiceBooking.data.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Integer> {
}
