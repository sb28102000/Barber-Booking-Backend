package com.barber.booking_service.repository;

import com.barber.booking_service.entity.Barber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BarberRepository extends JpaRepository<Barber, Long> {
    Optional<Barber> findByUsername(String username);
}