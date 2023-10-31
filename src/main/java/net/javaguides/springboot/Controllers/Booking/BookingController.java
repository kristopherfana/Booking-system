package net.javaguides.springboot.Controllers.Booking;

import net.javaguides.springboot.AppException;
import net.javaguides.springboot.model.BarberServiceModel;
import net.javaguides.springboot.model.Booking;
import net.javaguides.springboot.model.Schedule;
import net.javaguides.springboot.repository.BarberServiceRepositry;
import net.javaguides.springboot.repository.BookingRepository;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.Booking.BookingService;
import net.javaguides.springboot.web.dto.BookingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;
    private final BookingRepository bookingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BarberServiceRepositry barberServiceRepositry;

    public BookingController(BookingService bookingService,
                             BookingRepository bookingRepository){
        this.bookingService=bookingService;
        this.bookingRepository=bookingRepository;
    }
    @ModelAttribute("bookingDto")
    public BookingDto bookingDto(){
        return new BookingDto();
    }

    @GetMapping
    public String showBookingForm() {return "booking";}

    @PostMapping
    public String book(@ModelAttribute("bookingDto") BookingDto bookingDto){
        bookingService.save(bookingDto);
        System.out.println("Added 1");
        return "redirect:/booking?success";
    }
    @GetMapping("/all")
    public String showBookingList(Model model){
        List<Booking> bookingList= bookingRepository.findAll();
        model.addAttribute("bookings", bookingList);
        return "bookings";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") long id,
                               Model model){
        Booking booking=
                bookingRepository.findById(id).orElseThrow(()-> new AppException(
                        "Booking with booking id not found"+id));
        BookingDto bookingDto =
                new BookingDto(booking.getClient().getEmail(),
                        booking.getBarber().getEmail(),
                        booking.getService().getId(),
                        booking.getDate(),booking.getTime());
        model.addAttribute("booking", booking);
        model.addAttribute("bookingDto", bookingDto);
        return "update-booking";
    }

    @PostMapping("/update/{id}")
    public String updateService(@PathVariable("id") long id,
                                BookingDto bookingDto){
        bookingService.edit(bookingDto,id);
        return "redirect:/booking/all";
    }

}
