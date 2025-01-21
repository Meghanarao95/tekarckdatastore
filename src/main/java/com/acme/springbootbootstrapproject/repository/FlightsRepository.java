package com.acme.springbootbootstrapproject.repository;


import com.acme.springbootbootstrapproject.entity.Flights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightsRepository extends JpaRepository<Flights, Long> {
}
