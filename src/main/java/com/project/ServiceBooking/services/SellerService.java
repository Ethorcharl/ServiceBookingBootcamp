package com.project.ServiceBooking.services;

import com.project.ServiceBooking.data.Seller;
import com.project.ServiceBooking.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    @Autowired
    private final SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public Seller saveSeller(Seller seller){
        return sellerRepository.save(seller);
    }
}
