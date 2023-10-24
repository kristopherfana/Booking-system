package net.javaguides.springboot.Controllers.BarberService;

import net.javaguides.springboot.model.BarberServiceModel;
import net.javaguides.springboot.service.BarberService.BarberService;
import net.javaguides.springboot.web.dto.BarberServiceDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/service")
public class BarberServiceController {
    private BarberService barberService;

    public BarberServiceController(BarberService barberService) {
        super();
        this.barberService = barberService;
    }

    @ModelAttribute("barber_service")
    public BarberServiceDto serviceRegistrationDto() {
        return new BarberServiceDto();
    }

    @GetMapping("/add")
    public String showRegistrationForm() {
        return "service-registration";
    }

    @PostMapping("/add")
    public String addBarberService(@ModelAttribute("barber_service") BarberServiceDto barberServiceDto) {
        barberService.save(barberServiceDto);
        return "redirect:/service/add?success";
    }

    @GetMapping("/list")
    public String showBarberServiceList(Model model){
        List<BarberServiceModel> barberServiceList=barberService.listBarberServices();
        model.addAttribute("barberServiceList", barberServiceList);
        return "services";
    }
}
