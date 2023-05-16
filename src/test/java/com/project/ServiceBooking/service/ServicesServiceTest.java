package com.project.ServiceBooking.service;

import com.project.ServiceBooking.data.*;
import com.project.ServiceBooking.repositories.ServiceRepository;
import com.project.ServiceBooking.services.ServicesService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ServicesServiceTest {

    private int serviceId;
    private final ServiceRepository serviceRepository = Mockito.mock(ServiceRepository.class);

    private final ServicesService servicesService = new ServicesService(serviceRepository);

    @Test
    public void findByIdTest() {
        when(serviceRepository.findById(serviceId)).thenReturn(Optional.of(getTestService()));
        Service service = servicesService.findById(serviceId);
        assertEquals(22, service.getId());
    }
    @Test
    public void findAllTest() {
        when(serviceRepository.findAll()).thenReturn(getTestSeviceList());
        assertEquals(2, servicesService.findAllService().size());
    }
    @Test
    public void saveServiceTest(){
        Service serviceToSave = getTestService();
        when(serviceRepository.save(serviceToSave)).thenReturn(serviceToSave);
        assertEquals(getTestService().getId(), servicesService.saveService(serviceToSave).getId());
    }
    @Test
    public void deleteByIdTest() {
        assertDoesNotThrow(()->servicesService.deleteById(serviceId));
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

    private List<Service> getTestSeviceList() {
        List<Service> serviceList = new ArrayList<>();
        serviceList.add(getTestService());
        Service service = getTestService();
        service.setId(33);
        serviceList.add(service);
        return serviceList;
    }

}
