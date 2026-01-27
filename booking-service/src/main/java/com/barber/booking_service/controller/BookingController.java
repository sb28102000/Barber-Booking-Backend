package com.barber.booking_service.controller;

import com.barber.booking_service.entity.Barber;
import com.barber.booking_service.repository.AppointmentRepository;
import com.barber.booking_service.repository.BarberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class BookingController {
    @Autowired
    private BarberRepository barberRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping("/barbers")
    public List<Barber> getAllBarbers() {
        return barberRepository.findAll();
    }

    @PostMapping("/book")
    public com.barber.booking_service.entity.Appointment bookAppointment(@RequestBody com.barber.booking_service.entity.Appointment appointment) {
        appointment.setStatus("CONFIRMED");
        appointment.setAppointmentTime(java.time.LocalDateTime.now()); // Default to "NOW" for simplicity
        return appointmentRepository.save(appointment);
    }

    @GetMapping("/appointments")
    public List<com.barber.booking_service.entity.Appointment> getAllAppointments() {
        // In a real app, we would filter by 'customerName', but this works for now!
        return appointmentRepository.findAll();
    }

    @DeleteMapping("/appointment/{id}")
    public String deleteAppointment(@PathVariable Long id){
        appointmentRepository.deleteById(id);
        return "Delete Appointment Successfully.";
    }
}
