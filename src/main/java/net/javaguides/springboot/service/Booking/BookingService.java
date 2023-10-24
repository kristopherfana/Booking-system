package net.javaguides.springboot.service.Booking;

import net.javaguides.springboot.model.Booking;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.web.dto.BookingDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface BookingService {
    List<Booking> findAll();
    List<Booking> findBookingsByBarber(User barber);
    List<Booking> findBookingsByClient(User client);
    List<Booking> findBookingsByDate(LocalDate date);
    Optional<Booking> findBookingByTime(LocalTime time);

    Booking save(BookingDto bookingDto);
}
