package net.javaguides.springboot.service.User;

import net.javaguides.springboot.model.RoleName;
import org.springframework.security.core.userdetails.UserDetailsService;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.web.dto.UserRegistrationDto;

import java.util.List;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);

	List<User> listUsersByRole(RoleName roleName);

	List<User> listAllUsers();
}
