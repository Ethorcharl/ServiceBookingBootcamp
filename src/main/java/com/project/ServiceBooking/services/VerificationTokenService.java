package com.project.ServiceBooking.services;

import com.project.ServiceBooking.data.User;
import com.project.ServiceBooking.data.VerificationToken;
import com.project.ServiceBooking.repositories.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;

@Service
public class VerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;

    public VerificationTokenService(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    public void deleteById(Integer id) {
        verificationTokenRepository.deleteById(id);
    }
    public VerificationToken findByToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }


    public VerificationToken findByUser(User user) {
        return verificationTokenRepository.findByUser(user);
    }


    public void save(User user, String token) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        // set expiry date to 24 hours
        verificationToken.setExpiryDate(calculateExpiryDate(24*60));
        verificationTokenRepository.save(verificationToken);
    }

    //calculate expiry date
    private Timestamp calculateExpiryDate(int expiryTimeMinutes){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE,expiryTimeMinutes);
        return new Timestamp(cal.getTime().getTime());
    }
}
