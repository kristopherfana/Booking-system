package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Booking;
import net.javaguides.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking,
        Long> {
    List<Booking> findAll();
    List<Booking> findBookingsByBarber(User barber);
    List<Booking> findBookingsByClient(User client);
    List<Booking> findBookingsByDate(LocalDate date);
    Optional<Booking> findBookingByTime(LocalTime time);
    List<Booking> findBookingsByDateAndBarber_Email(LocalDate date,
                                                    String barberEmail);

}
