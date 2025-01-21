package com.acme.springbootbootstrapproject.repository;

import com.acme.springbootbootstrapproject.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
}
