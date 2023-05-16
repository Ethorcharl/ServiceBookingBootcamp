package com.project.ServiceBooking.services;

import com.project.ServiceBooking.data.ServicesCategory;
import com.project.ServiceBooking.repositories.ServicesCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicesCategoryService {

    @Autowired
    ServicesCategoryRepository servicesCategoryRepository;

    public List<ServicesCategory> getAllServices() {
        return servicesCategoryRepository.findAll();
    }

    public Optional<ServicesCategory> findById(Integer id){
        return servicesCategoryRepository.findById(id);
    }
    public ServicesCategory saveServicesCategory(ServicesCategory servicesCategory){
        return servicesCategoryRepository.save(servicesCategory);
    }
    public void deleteBtId(Integer id){
        servicesCategoryRepository.deleteById(id);
    }

/*    public ServicesCategory getServices() {
        ServicesCategory servicesCategory = servicesCategoryRepository.findById(1).get();
        return servicesCategory;
    }*/
}
