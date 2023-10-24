package net.javaguides.springboot.service.User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import net.javaguides.springboot.AppException;
import net.javaguides.springboot.model.RoleName;
import net.javaguides.springboot.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Role;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.web.dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		super();
		this.userRepository = userRepository;
		this.roleRepository= roleRepository;
	}

	@Override
	public User save(UserRegistrationDto userRegistrationDto) {
		System.out.println("User role is"+userRegistrationDto.getRoleName());
		RoleName roleName=
				RoleName.valueOf(userRegistrationDto.getRoleName());
		if(roleName == null){
			roleName=RoleName.ROLE_USER;
		}
		System.out.println("User role now is"+roleName);
		Role userRole =
				roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("User Role not set."));
		User user = new User(userRegistrationDto.getFirstName(),
				userRegistrationDto.getLastName(), userRegistrationDto.getEmail(),
				passwordEncoder.encode(userRegistrationDto.getPassword()), userRegistrationDto.getPhoneNumber(), Collections.singleton(userRole));
		return userRepository.save(user);
	}

	@Override
	public List<User> listUsersByRole(RoleName roleName){
		List<User> userListByRoles = userRepository.findByRoles(roleRepository.findByName(roleName).orElseThrow(() -> new AppException("User Role not set.")));
		for(User user: userListByRoles){
			System.out.println(user);
		}
		return userListByRoles;
	}



	@Override
	public List<User> listAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user = userRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));		
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
	}

}
