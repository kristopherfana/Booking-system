package net.javaguides.springboot.Controllers.User;

import net.javaguides.springboot.AppException;
import net.javaguides.springboot.model.RoleName;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.User.UserService;
import net.javaguides.springboot.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired private UserService userService;
    @Autowired private UserRepository userRepository;

    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> userList=userService.listAllUsers();
        model.addAttribute("userList", userList);

        return "users";
    }

    @GetMapping("users/{role}")
    public String showUsersByRole(@PathVariable("role") String roleName, Model model){
        List<User> userList=userService.listUsersByRole(RoleName.valueOf(roleName));
        model.addAttribute("role", roleName);
        model.addAttribute("userList", userList);

        return "users";
    }

    @GetMapping("user/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("user/update/{id}")
    public String updateUser(@PathVariable("id") long id,
                             User user) {
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("user/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        return "redirect:/users";
    }


}
