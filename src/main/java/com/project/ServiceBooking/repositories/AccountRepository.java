package com.project.ServiceBooking.repositories;

import com.project.ServiceBooking.data.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
