package com.project.ServiceBooking.service;

import com.project.ServiceBooking.data.*;
import com.project.ServiceBooking.repositories.VerificationTokenRepository;
import com.project.ServiceBooking.services.VerificationTokenService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class VerificationTokenServiceTest {

    private int verId;
    private String token = "Ruhm";

    private final VerificationTokenRepository verificationTokenRepository = Mockito.mock(VerificationTokenRepository.class);
    private final VerificationTokenService verificationTokenService = new VerificationTokenService(verificationTokenRepository);

    @Test
    public void findByTokenTest() {
        when(verificationTokenRepository.findByToken(token)).thenReturn((getTestVerification()));
        VerificationToken verificationToken = verificationTokenService.findByToken(token);
        assertEquals("Ruhm", verificationToken.getToken());
    }
    @Test
    public void findByUserTest() {
        when(verificationTokenRepository.findByUser(getTestUser())).thenReturn((getTestVerification()));
        VerificationToken verificationToken = verificationTokenService.findByUser(getTestUser());
        assertEquals(getTestUser(), verificationToken.getUser());

    }
    @Test
    public void deleteByIdTest() {
        assertDoesNotThrow(()->verificationTokenService.deleteById(verId));
    }

    @Test
    public void saveVerification(){

        VerificationToken verificationToken = new VerificationToken(getTestUser(),token);
        when(verificationTokenRepository.save(verificationToken)).thenReturn(verificationToken);
        assertEquals(getTestVerification().getToken(), verificationTokenRepository.save(verificationToken).getToken());

    }


    private VerificationToken getTestVerification() {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setTokenId(22);
        verificationToken.setToken("Ruhm");
        verificationToken.setExpiryDate(new Timestamp(System.currentTimeMillis()));
        verificationToken.setUser(getTestUser());
        return verificationToken;
    }

    private List<VerificationToken> getTestVerificationList() {
        List<VerificationToken> verificationTokenList = new ArrayList<>();
        verificationTokenList.add(getTestVerification());
        VerificationToken verificationToken = getTestVerification();
        verificationToken.setTokenId(33);
        return verificationTokenList;
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
