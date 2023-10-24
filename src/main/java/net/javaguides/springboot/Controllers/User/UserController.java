package net.javaguides.springboot.Controllers.User;

import net.javaguides.springboot.model.RoleName;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserController {
    @Autowired private UserService userService;

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

}
