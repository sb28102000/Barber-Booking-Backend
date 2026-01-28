package com.barber.booking_service.entity;

import com.barber.booking_service.common.AppointmentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long barberId;
    private String customerName; // We will grab this from the JWT later
    private LocalDateTime appointmentTime;
    @Enumerated(EnumType.STRING) // Saves as "CONFIRMED" in DB
    private AppointmentStatus status;
    private String purpose;
    private Double price;
    private String customerMobile;
}
