package com.project.ServiceBooking.repositories;

import com.project.ServiceBooking.data.Account;
import com.project.ServiceBooking.data.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
