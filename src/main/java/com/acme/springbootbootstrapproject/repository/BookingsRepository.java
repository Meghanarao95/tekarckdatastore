package com.acme.springbootbootstrapproject.repository;


import com.acme.springbootbootstrapproject.entity.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingsRepository extends JpaRepository<Bookings, Long> {

    // Derived query to find bookings by user ID
    List<Bookings> findByUserId(Long userId);
}
