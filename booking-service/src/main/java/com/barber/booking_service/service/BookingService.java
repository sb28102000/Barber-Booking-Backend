package com.barber.booking_service.service;

import com.barber.booking_service.entity.Appointment;
import com.barber.booking_service.entity.Barber;

import java.util.List;

public interface BookingService {
    List<Barber> getAllBarbers();
    List<Appointment> getAllAppointments();
    Appointment createAppointment(Appointment appointment);
    void cancelAppointment(Long id);
    Appointment updateAppointmentStatus(Long id, String status);
}
