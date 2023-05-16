package com.project.ServiceBooking.repositories;


import com.project.ServiceBooking.data.ServicesCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicesCategoryRepository extends JpaRepository<ServicesCategory, Integer> {
}
