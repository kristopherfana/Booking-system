package net.javaguides.springboot.Controllers.Schedule;

import net.javaguides.springboot.model.Schedule;
import net.javaguides.springboot.service.Schedule.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired private ScheduleService scheduleService;
    @GetMapping("/all")
    public String showScheduleList(Model model){
        List<Schedule> scheduleList= scheduleService.findAll();
        for(Schedule schedule : scheduleList){
            System.out.println(schedule);
        }
        model.addAttribute("scheduleList", scheduleList);
        return "users";
    }

    @GetMapping("/{email}")
    public String showScheduleListByBarber(@PathVariable("email") String email, Model model){
        List<Schedule> scheduleListByBarber= scheduleService.findByBarber(email);
        model.addAttribute("scheduleListByBarber",
                scheduleListByBarber);
        return "schedules";
    }
}
