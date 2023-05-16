package com.project.ServiceBooking.controller;


import com.project.ServiceBooking.data.*;
import com.project.ServiceBooking.repositories.ServiceRepository;
import com.project.ServiceBooking.repositories.ServicesCategoryRepository;
import com.project.ServiceBooking.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.hibernate.Hibernate.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ServiceControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private ServiceRepository serviceRepository;
    @MockBean
    private ServicesCategoryRepository servicesCategoryRepository;


    @Test
    public void testUpdateService() throws Exception {
        Service service = getTestService();

        when(serviceRepository.save(service)).thenReturn(getTestService());
        assertEquals(getTestService().getId(), serviceRepository.save(service).getId());

        mockMvc.perform(post("/updateService")
                        .flashAttr("service", service))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/services"));
    }

    @Test
    public void testUpdateServiceForm() throws Exception {
        Service service = getTestService();
        int serviceId = 22;

        when(serviceRepository.findById(serviceId)).thenReturn(Optional.of(getTestService()));
        Service service1 = serviceRepository.findById(serviceId).orElse(null);
        assertEquals(getTestService().getId(), service1.getId());

        when(servicesCategoryRepository.findAll()).thenReturn((getTestCategoryList()));
        assertEquals(2, servicesCategoryRepository.findAll().size());

        mockMvc.perform(get("/updateService/22"))
                .andExpect(status().isOk())
                .andExpect(view().name("updateService"))
                .andExpect(model().attributeExists("service"))
                .andExpect(model().attributeExists("servicesCategories"));
    }

    @Test
    @WithMockUser(authorities = {"specialist:write"})
    public void testCreateServiceForm() throws Exception {
        Service service = getTestService();

        when(servicesCategoryRepository.findAll()).thenReturn((getTestCategoryList()));
        assertEquals(2, servicesCategoryRepository.findAll().size());

        mockMvc.perform(get("/createService"))
                .andExpect(status().isOk())
                .andExpect(view().name("createService"))
                .andExpect(model().attributeExists("service"))
                .andExpect(model().attributeExists("servicesCategories"));
    }

    @Test
    @WithMockUser(authorities = {"specialist:write"})
    public void testCreateService() throws Exception {
        Service service = getTestService();

        when(userRepository.findByEnterEmail("Ruhm")).thenReturn(getTestUser());
        assertEquals(getTestUser(), userRepository.findByEnterEmail("Ruhm"));

        when(serviceRepository.save(service)).thenReturn(getTestService());
        assertEquals(getTestService().getId(), serviceRepository.save(service).getId());

        mockMvc.perform(post("/createService")
                        .flashAttr("service", service))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/services"));
    }


    private Service getTestService() {
        Service service = new Service();
        service.setId(22);
        service.setName("Ruhm");
        service.setPrice(33F);
        service.setAvgrating(33F);
        service.setUsersIdUsers(new User());
        service.setIdServicesCategory(new ServicesCategory());
        service.setDescription("Ruhm");
        service.setCountreview(22);
        return service;
    }

    private ServicesCategory getTestCategory() {
        ServicesCategory servicesCategory = new ServicesCategory();
        servicesCategory.setId(22);
        servicesCategory.setCategory("Ruhm");
        servicesCategory.setSubCategory("Ruhm");
        return servicesCategory;
    }

    private List<ServicesCategory> getTestCategoryList() {
        List<ServicesCategory> servicesCategoryList = new ArrayList<>();
        servicesCategoryList.add(getTestCategory());
        ServicesCategory servicesCategory = getTestCategory();
        servicesCategory.setId(33);
        servicesCategoryList.add(servicesCategory);
        return servicesCategoryList;
    }

    private List<Service> getTestSeviceList() {
        List<Service> serviceList = new ArrayList<>();
        serviceList.add(getTestService());
        Service service = getTestService();
        service.setId(33);
        serviceList.add(service);
        return serviceList;
    }

    private User getTestUser() {
        User user = new User();
        user.setId(22);
        user.setName("Ruhm");
        user.setSurname("Ruhm");
        user.setEmail("Ruhm");
        user.setPassword("Ruhm");
        user.setStatus(Status.ACTIVE);
        user.setRole(Role.CLIENT);
        user.setPicture("Ruhm");
        user.setAccountIdaccount(new Account());
        user.setSellerIdSeller(new Seller());
        return user;
    }
}
