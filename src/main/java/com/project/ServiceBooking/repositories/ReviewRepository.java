package com.project.ServiceBooking.repositories;

import com.project.ServiceBooking.data.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query(value = "select * from review r where r.id_services = :idServices",nativeQuery = true)
    List<Review> findByidServices(@Param("idServices")Integer idServices);
}
