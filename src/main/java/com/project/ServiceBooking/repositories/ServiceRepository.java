package com.project.ServiceBooking.repositories;

import com.project.ServiceBooking.data.Service;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ServiceRepository extends JpaRepository<Service, Integer> {

}

