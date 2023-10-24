package net.javaguides.springboot.service.Booking;

import net.javaguides.springboot.AppException;
import net.javaguides.springboot.model.Booking;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.BarberServiceRepositry;
import net.javaguides.springboot.repository.BookingRepository;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.web.dto.BookingDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;
    private UserRepository userRepository;
    private BarberServiceRepositry barberServiceRepositry;

    public BookingServiceImpl(BookingRepository bookingRepository,
                              UserRepository userRepository,
                              BarberServiceRepositry barberServiceRepositry){
        this.bookingRepository=bookingRepository;
        this.userRepository=userRepository;
        this.barberServiceRepositry=barberServiceRepositry;

    }
    @Override
    public List<Booking> findAll() {
        return null;
    }

    @Override
    public List<Booking> findBookingsByBarber(User barber) {
        return null;
    }

    @Override
    public List<Booking> findBookingsByClient(User client) {
        return null;
    }

    @Override
    public List<Booking> findBookingsByDate(LocalDate date) {
        return null;
    }

    @Override
    public Optional<Booking> findBookingByTime(LocalTime time) {
        return Optional.empty();
    }

    @Override
    public Booking save(BookingDto bookingDto) {
        Booking booking= new Booking(
                bookingDto.getDate(),
                bookingDto.getTime()
        );

        booking.setBarber(userRepository.findByEmail(bookingDto.getBarberEmail()));
        booking.setService(barberServiceRepositry.findById(bookingDto.getServiceId()).orElseThrow(()->new AppException("Barber not found")));
        booking.setClient(userRepository.findByEmail(bookingDto.getClientEmail()));
        System.out.println(booking);
        return bookingRepository.save(booking);

    }
}
