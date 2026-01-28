package com.barber.booking_service.controller;

import com.barber.booking_service.entity.Appointment;
import com.barber.booking_service.entity.Barber;
import com.barber.booking_service.service.BarberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking/barber")
@CrossOrigin(origins = "*")
public class BarberController {

    @Autowired
    private BarberService barberService;

    @GetMapping("/profile/{username}")
    public Barber getProfile(@PathVariable String username) {
        return barberService.getBarberProfile(username);
    }

    @PostMapping("/profile")
    public Barber updateProfile(@RequestBody Barber barber) {
        return barberService.updateBarberProfile(barber);
    }

    @GetMapping("/appointments/{username}")
    public List<Appointment> getBarberAppointments(@PathVariable String username) {
        return barberService.getAppointmentsForBarber(username);
    }
}
