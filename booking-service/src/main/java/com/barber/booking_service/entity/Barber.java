package com.barber.booking_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "barbers")
public class Barber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String specialty;
    private String address;
    private String description;
    @Column(unique = true)
    private String username;
    private Double basePrice;
    private String mobileNumber;
}
