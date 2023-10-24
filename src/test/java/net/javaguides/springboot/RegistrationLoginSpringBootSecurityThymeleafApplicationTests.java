package net.javaguides.springboot;

import net.javaguides.springboot.model.Booking;
import net.javaguides.springboot.model.RoleName;
import net.javaguides.springboot.repository.BookingRepository;
import net.javaguides.springboot.repository.RoleRepository;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.User.UserService;
import net.javaguides.springboot.web.dto.UserRegistrationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
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

	@Test
	public void listScheduleByDay(){
		List<Booking> bookings =
				bookingRepository.findBookingsByDate(LocalDate.parse("2023-10-22",
						DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		System.out.println(bookings);
	}

	@Test
	public void addUserWithRole(){
		UserRegistrationDto userRegistrationDto =
				new UserRegistrationDto("Kristopher", "Fana",
						"kfana@gmail.com","12345678","0777344737",
						RoleName.ROLE_BARBER);
		userService.save(userRegistrationDto);
	}

}
