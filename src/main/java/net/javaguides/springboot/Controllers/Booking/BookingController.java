package net.javaguides.springboot.Controllers.Booking;

import net.javaguides.springboot.service.Booking.BookingService;
import net.javaguides.springboot.web.dto.BookingDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/booking")
public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService=bookingService;
    }

    @ModelAttribute("booking")
    public BookingDto bookingDto(){
        return new BookingDto();
    }

    @GetMapping
    public String showBookingForm() {return "booking";}

    @PostMapping
    public String book(@ModelAttribute("booking") BookingDto bookingDto){
        bookingService.save(bookingDto);
        System.out.println("Added 1");
        return "redirect:/booking?success";
    }
}
