package net.javaguides.springboot;

import net.javaguides.springboot.model.Booking;
import net.javaguides.springboot.model.BookingStatus;
import net.javaguides.springboot.model.RoleName;
import net.javaguides.springboot.model.Schedule;
import net.javaguides.springboot.repository.BookingRepository;
import net.javaguides.springboot.repository.RoleRepository;
import net.javaguides.springboot.repository.ScheduleRepository;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.Booking.BookingService;
import net.javaguides.springboot.service.Schedule.ScheduleService;
import net.javaguides.springboot.service.User.UserService;
import net.javaguides.springboot.web.dto.BookingDto;
import net.javaguides.springboot.web.dto.UserRegistrationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootTest
class RegistrationLoginSpringBootSecurityThymeleafApplicationTests {

	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private ScheduleRepository scheduleRepository;
	@Autowired
	private ScheduleService scheduleService;
	@Autowired
	private BookingService bookingService;

	@Test
	public void listScheduleByDay(){
		List<Booking> bookings =
				bookingRepository.findBookingsByDate(LocalDate.parse("2023-10-22",
						DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		System.out.println(bookings);
	}


	@Test
	public void findScheduleByDateAndTime(){
		BookingDto bookingDto = new BookingDto("kfana@africau.edu",
				"tawandan@africau.edu",1L,LocalDate.parse("2023-10" +
						"-16",
				DateTimeFormatter.ofPattern("yyyy-MM-dd")),"09:00" +
				":00");
		Schedule schedule=
				scheduleRepository.findByDateAndStartTime(bookingDto.getDate(), LocalTime.parse(bookingDto.getTime(),DateTimeFormatter.ofPattern("HH:mm:ss"))).orElseThrow(()-> new AppException("Schedule not found"));
		schedule.setStatus(BookingStatus.Booked);
		System.out.println(schedule);
		bookingService.save(bookingDto);

	}

}
