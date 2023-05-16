package com.project.ServiceBooking.controllers;

import com.project.ServiceBooking.data.Service;
import com.project.ServiceBooking.data.ServicesCategory;
import com.project.ServiceBooking.data.User;
import com.project.ServiceBooking.services.ServicesCategoryService;
import com.project.ServiceBooking.services.ServicesService;
import com.project.ServiceBooking.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class ServiceController {
    private final ServicesService servicesService;
    private final ServicesCategoryService servicesCategoryService;
    private final UserService userService;

    public ServiceController(ServicesService servicesService, ServicesCategoryService servicesCategoryService, UserService userService) {
        this.servicesService = servicesService;
        this.servicesCategoryService = servicesCategoryService;
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('specialist:write')")
    @GetMapping("/createService")
    public String createServiceForm(Model model) {
        List<ServicesCategory> servicesCategories = servicesCategoryService.getAllServices();
        model.addAttribute("servicesCategories", servicesCategories);
        model.addAttribute("service", new Service());
        return "createService";
    }

    @PreAuthorize("hasAuthority('specialist:write')")
    @PostMapping("/createService")
    public String createService(Service service) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            User user = userService.findByEnterEmail(currentUserName);
            service.setUsersIdUsers(user);
            servicesService.saveService(service);
        }
        return "redirect:/services";
    }


//    @GetMapping("/updateService/{id}")
//    private String updateServiceForm(@PathVariable("id") Integer id, Model model) {
//        Service service = servicesService.findById(id);
//        model.addAttribute("service", service);
//        List<ServicesCategory> servicesCategories = servicesCategoryService.getAllServices();
//        model.addAttribute("servicesCategories", servicesCategories);
//        return "updateService";
//    }

    @GetMapping("/updateService/{id}")
    public String updateServiceForm(@PathVariable("id") Integer id, Model model) {
        Service service = servicesService.findById(id);
        model.addAttribute("service", service);
        List<ServicesCategory> servicesCategories = servicesCategoryService.getAllServices();
        model.addAttribute("servicesCategories", servicesCategories);
        return "updateService";
    }

    @PostMapping("/updateService")
    public String updateService(Service service) {
        servicesService.saveService(service);
        return "redirect:/services";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteService(@PathVariable("id") Integer id, Model model){
        servicesService.deleteById(id);
        return "redirect:/services";
    }


}
