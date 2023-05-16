package com.project.ServiceBooking.controllers;

import com.project.ServiceBooking.data.Booking;
import com.project.ServiceBooking.data.Service;
import com.project.ServiceBooking.data.User;
import com.project.ServiceBooking.services.BookingService;
import com.project.ServiceBooking.services.ServicesService;
import com.project.ServiceBooking.services.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class BookingController {
    private final BookingService bookingService;
    private final ServicesService servicesService;
    private final UserService userService;


    public BookingController(BookingService bookingService, ServicesService servicesService, UserService userService) {
        this.bookingService = bookingService;
        this.servicesService = servicesService;
        this.userService = userService;
    }

    @GetMapping("/services/description/createBookingForm/{serviceId}/{authorId}")
    public String createBookingForm(@PathVariable("serviceId") int serviceId,
                                    @PathVariable("authorId") int authorId,
                                    Model model) {
//        model.addAttribute("serviceId", serviceId);
//        model.addAttribute("authorId", authorId);
        Booking booking = new Booking();
        booking.setIdSeller(authorId);
        booking.setIdServices(servicesService.findById(serviceId));
        model.addAttribute("booking", booking);
        return "booking.html";
    }

    @PostMapping("/createBookingSave")
    public String createBooking( Booking booking) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            User user = userService.findByEnterEmail(currentUserName);
            booking.setIdUsers(user);
//            booking.setIdServices(servicesService.findById(serviceId));
//            booking.setIdSeller(authorId);
            bookingService.saveBooking(booking);
        }
        return "HomePage.html";
    }
}
