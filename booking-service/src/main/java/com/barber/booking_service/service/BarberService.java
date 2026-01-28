package com.barber.booking_service.service;

import com.barber.booking_service.entity.Appointment;
import com.barber.booking_service.entity.Barber;

import java.util.List;

public interface BarberService {
    Barber getBarberProfile(String username);
    Barber updateBarberProfile(Barber barber);
    List<Appointment> getAppointmentsForBarber(String username);
}
