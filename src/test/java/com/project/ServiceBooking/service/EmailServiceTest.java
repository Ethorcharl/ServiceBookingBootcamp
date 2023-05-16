package com.project.ServiceBooking.service;

import com.project.ServiceBooking.data.*;
import com.project.ServiceBooking.repositories.VerificationTokenRepository;
import com.project.ServiceBooking.services.VerificationTokenService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class EmailServiceTest {

    private final VerificationTokenRepository verificationTokenRepository = Mockito.mock(VerificationTokenRepository.class);
    private final VerificationTokenService verificationTokenService = new VerificationTokenService(verificationTokenRepository);

    @Test
    public void sentHtmlMailTest(){
        when(verificationTokenRepository.findByUser(getTestUser())).thenReturn((getTestVerification()));
        VerificationToken verificationToken = verificationTokenService.findByUser(getTestUser());
        assertEquals(getTestUser(), verificationToken.getUser());

    }


    private VerificationToken getTestVerification() {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setTokenId(22);
        verificationToken.setToken("Ruhm");
        verificationToken.setExpiryDate(new Timestamp(System.currentTimeMillis()));
        verificationToken.setUser(getTestUser());
        return verificationToken;
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
