package com.barber.booking_service.serviceImpl;

import com.barber.booking_service.entity.Appointment;
import com.barber.booking_service.entity.Barber;
import com.barber.booking_service.repository.AppointmentRepository;
import com.barber.booking_service.repository.BarberRepository;
import com.barber.booking_service.service.BarberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BarberServiceImpl implements BarberService {

    @Autowired
    private BarberRepository barberRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Barber getBarberProfile(String username) {
        return barberRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Barber profile not found"));
    }

    @Override
    public Barber updateBarberProfile(Barber barber) {
        Optional<Barber> existingOpt = barberRepository.findByUsername(barber.getUsername());

        if (existingOpt.isPresent()) {
            Barber existing = existingOpt.get();
            existing.setName(barber.getName());
            existing.setSpecialty(barber.getSpecialty());
            existing.setAddress(barber.getAddress());
            existing.setDescription(barber.getDescription());
            existing.setBasePrice(barber.getBasePrice()); // Update Price
            return barberRepository.save(existing);
        } else {
            return barberRepository.save(barber);
        }
    }

    @Override
    public List<Appointment> getAppointmentsForBarber(String username) {
        Barber barber = barberRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Barber not found"));

        // We need a custom query in Repository to find by Barber ID
        return appointmentRepository.findByBarberId(barber.getId());
    }
}
