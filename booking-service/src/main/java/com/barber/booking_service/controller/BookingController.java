package com.barber.booking_service.controller;

import com.barber.booking_service.common.AppointmentStatus;
import com.barber.booking_service.entity.Appointment;
import com.barber.booking_service.entity.Barber;
import com.barber.booking_service.repository.AppointmentRepository;
import com.barber.booking_service.repository.BarberRepository;
import com.barber.booking_service.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("/barbers")
    public List<Barber> getBarbers() {
        return bookingService.getAllBarbers();
    }

    @GetMapping("/appointments")
    public List<Appointment> getAppointments() {
        return bookingService.getAllAppointments();
    }

    @PostMapping("/book")
    public Appointment bookAppointment(@RequestBody Appointment appointment) {
        return bookingService.createAppointment(appointment);
    }

    @DeleteMapping("/appointment/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        bookingService.cancelAppointment(id);
        return "Appointment deleted successfully";
    }

    @PutMapping("/appointment/{id}/status")
    public Appointment updateStatus(@PathVariable Long id, @RequestParam String status) {
        return bookingService.updateAppointmentStatus(id, status);
    }
}
