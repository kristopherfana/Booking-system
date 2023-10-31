package net.javaguides.springboot.service.Booking;

import net.javaguides.springboot.AppException;
import net.javaguides.springboot.model.Booking;
import net.javaguides.springboot.model.BookingStatus;
import net.javaguides.springboot.model.Schedule;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.BarberServiceRepositry;
import net.javaguides.springboot.repository.BookingRepository;
import net.javaguides.springboot.repository.ScheduleRepository;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.web.dto.BookingDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final BarberServiceRepositry barberServiceRepositry;
    private final ScheduleRepository scheduleRepository;

    public BookingServiceImpl(BookingRepository bookingRepository,
                              UserRepository userRepository,
                              BarberServiceRepositry barberServiceRepositry, ScheduleRepository scheduleRepository){
        this.bookingRepository=bookingRepository;
        this.userRepository=userRepository;
        this.barberServiceRepositry=barberServiceRepositry;
        this.scheduleRepository=scheduleRepository;

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

        return getBooking(bookingDto, booking);

    }

    @Override
    public Booking edit(BookingDto bookingDto, Long id){
        Booking booking = new Booking(
                bookingDto.getDate(),
                bookingDto.getTime()
        );
        booking.setId(id);
        return getBooking(bookingDto, booking);

    }

    private Booking getBooking(BookingDto bookingDto, Booking booking) {
        booking.setBarber(userRepository.findByEmail(bookingDto.getBarberEmail()));
        booking.setService(barberServiceRepositry.findById(bookingDto.getServiceId()).orElseThrow(()->new AppException("Barber not found")));
        booking.setClient(userRepository.findByEmail(bookingDto.getClientEmail()));
        setScheduleStatus(bookingDto);
        return bookingRepository.save(booking);
    }

    private void setScheduleStatus(BookingDto bookingDto){
        Schedule schedule=
                scheduleRepository.findByDateAndStartTime(bookingDto.getDate(), LocalTime.parse(bookingDto.getTime(), DateTimeFormatter.ofPattern("HH:mm"))).orElseThrow(()-> new AppException("Schedule not found"));
        System.out.println(schedule.getStatus());
        if (schedule.getStatus()==null){
            schedule.setStatus(BookingStatus.Booked);
        }
    }
}
