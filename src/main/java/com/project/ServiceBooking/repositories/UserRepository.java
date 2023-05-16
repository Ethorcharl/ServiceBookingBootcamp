package com.project.ServiceBooking.repositories;


import com.project.ServiceBooking.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
    @Query(value = "select * from users u where u.email like %:enterEmail% ", nativeQuery = true)//without nativeQuery doesn't  work
    User findByEnterEmail(@Param("enterEmail") String enterEmail);
}
