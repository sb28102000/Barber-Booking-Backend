package com.barber.booking_service.entity;

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
    private String status; // e.g., "CONFIRMED"
}
