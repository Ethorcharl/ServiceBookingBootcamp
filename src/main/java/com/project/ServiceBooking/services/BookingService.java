package com.project.ServiceBooking.services;

import com.project.ServiceBooking.data.Booking;
import com.project.ServiceBooking.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }
    public Booking findById(Integer id){
        return bookingRepository.findById(id).orElse(null);
    }
    public List<Booking> findAllBooking(){
        return bookingRepository.findAll();
    }
    public Booking saveBooking(Booking booking){
        return bookingRepository.save(booking);
    }
    public void deleteById(Integer id){
        bookingRepository.deleteById(id);
    }
}
