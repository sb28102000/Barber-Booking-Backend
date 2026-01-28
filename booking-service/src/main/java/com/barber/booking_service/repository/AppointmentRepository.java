package com.barber.booking_service.repository;

import com.barber.booking_service.common.AppointmentStatus;
import com.barber.booking_service.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    boolean existsByBarberIdAndStatus(Long barberId, AppointmentStatus status);
    List<Appointment> findByBarberId(Long barberId);

}
