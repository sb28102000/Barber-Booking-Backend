package com.barber.booking_service.serviceImpl;

import com.barber.booking_service.common.AppointmentStatus;
import com.barber.booking_service.entity.Appointment;
import com.barber.booking_service.entity.Barber;
import com.barber.booking_service.repository.AppointmentRepository;
import com.barber.booking_service.repository.BarberRepository;
import com.barber.booking_service.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BarberRepository barberRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public List<Barber> getAllBarbers() {
        return barberRepository.findAll();
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {
        // CHECK: Is the barber already booked?
        boolean isBusy = appointmentRepository.existsByBarberIdAndStatus(
                appointment.getBarberId(),
                AppointmentStatus.CONFIRMED
        );

        if (isBusy) {
            // This message will be sent to the Frontend Popup
            throw new RuntimeException("This barber is currently busy! Please choose another.");
        }

        appointment.setAppointmentTime(LocalDateTime.now());
        appointment.setStatus(AppointmentStatus.CONFIRMED);
        return appointmentRepository.save(appointment);
    }

    @Override
    public void cancelAppointment(Long id) {
        // Business Logic: Check if exists before deleting
        if(appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Appointment not found with ID: " + id);
        }
    }

    @Override
    public Appointment updateAppointmentStatus(Long id, String status) {
        // 1. Find the appointment
        Appointment appt = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        // 2. Change the status (Convert String to Enum)
        appt.setStatus(AppointmentStatus.valueOf(status));

        // 3. Save directly (skipping the createAppointment logic)
        return appointmentRepository.save(appt);
    }
}
