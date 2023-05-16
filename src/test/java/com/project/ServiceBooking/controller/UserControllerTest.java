package com.project.ServiceBooking.controller;


import com.icegreen.greenmail.base.GreenMailOperations;
import com.icegreen.greenmail.junit.GreenMailRule;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;
import com.icegreen.greenmail.util.ServerSetupTest;
import com.project.ServiceBooking.config.SecurityConfig;
import com.project.ServiceBooking.controllers.UserController;
import com.project.ServiceBooking.data.*;
import com.project.ServiceBooking.repositories.AccountRepository;
import com.project.ServiceBooking.repositories.SellerRepository;
import com.project.ServiceBooking.repositories.UserRepository;
import com.project.ServiceBooking.repositories.VerificationTokenRepository;
import com.project.ServiceBooking.services.*;

import jakarta.mail.Message;
import jakarta.mail.internet.MimeMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

//    @MockBean
//    private AccountRepository accountRepository;
//    @MockBean
//    private SellerRepository sellerRepository;

    @MockBean
    private VerificationTokenRepository verificationTokenRepository;


    @Test
    public void testShowRegistrationFormSpecialist() throws Exception {
        mockMvc.perform(get("/auth/registration_specialist"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("user", any(User.class)));
    }

    @Test
    public void testShowRegistrationFormClient() throws Exception {
        mockMvc.perform(get("/auth/registration_client"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("user", any(User.class)));
    }


//    @RegisterExtension
//    public static final GreenMailExtension greenMail = new GreenMailExtension(ServerSetupTest.SMTP);

    @Test
    public void testAddClient() throws Exception {
        User user = getTestUser();

        when(userRepository.findByEnterEmail("Schmetterling")).thenReturn(getTestUser());
        assertEquals(getTestUser(), userRepository.findByEnterEmail("Schmetterling"));

        when(userRepository.save(getTestUser())).thenReturn(getTestUser());
        assertEquals(getTestUser(), userRepository.save(getTestUser()));

        VerificationToken verificationToken = null;
        when(verificationTokenRepository.findByUser(user)).thenReturn(verificationToken);

        mockMvc.perform(post("/auth/process_register_client")
                        .param("email", user.getEmail())
                        .param("password", user.getPassword())
                        .param("name", user.getName())
                        .param("surname", user.getSurname())
                        .param("role", user.getRole().toString())
                        .param("status", user.getStatus().toString())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("/login"))
                .andExpect(model().attributeExists("message"));

    }

    @Test
    public void testAddSpecialist() throws Exception {
        User user = getTestUser();
        String token = UUID.randomUUID().toString();

        VerificationToken verificationToken = new VerificationToken(user, token);

        when(userRepository.findByEnterEmail("Schmetterling")).thenReturn(getTestUser());
        assertEquals(getTestUser(), userRepository.findByEnterEmail("Schmetterling"));

        when(userRepository.save(getTestUser())).thenReturn(getTestUser());
        assertEquals(getTestUser(), userRepository.save(getTestUser()));

        when(verificationTokenRepository.save(verificationToken)).thenReturn(verificationToken);
        assertEquals(verificationToken, verificationTokenRepository.save(verificationToken));

        when(verificationTokenRepository.findByUser(user)).thenReturn(verificationToken);
        assertEquals(verificationToken, verificationTokenRepository.findByUser(user));

        mockMvc.perform(post("/auth/process_register_client")
                        .param("email", user.getEmail())
                        .param("password", user.getPassword())
                        .param("name", user.getName())
                        .param("surname", user.getSurname())
                        .param("role", user.getRole().toString())
                        .param("status", user.getStatus().toString())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("/login"))
                .andExpect(model().attributeExists("message"));

    }

    @Test
    public void testActivation() throws Exception {
        User user = getTestUser();

        String token = UUID.randomUUID().toString();

        VerificationToken verificationToken = new VerificationToken(user, token);

        when(verificationTokenRepository.findByToken(token)).thenReturn(verificationToken);
        assertEquals(verificationToken, verificationTokenRepository.findByToken(token));

        assertDoesNotThrow(() -> verificationTokenRepository.deleteById(verificationToken.getTokenId()));

        when(verificationTokenRepository.save(verificationToken)).thenReturn(verificationToken);
        assertEquals(verificationToken, verificationTokenRepository.save(verificationToken));

        when(userRepository.save(getTestUser())).thenReturn(getTestUser());
        assertEquals(getTestUser(), userRepository.save(getTestUser()));

        when(verificationTokenRepository.findByUser(user)).thenReturn(verificationToken);
        assertEquals(verificationToken, verificationTokenRepository.findByUser(user));

        when(userRepository.save(getTestUser())).thenReturn(getTestUser());
        assertEquals(getTestUser(), userRepository.save(getTestUser()));

        mockMvc.perform(get("/auth/activation")
                        .param("token", token)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("activation"))
                .andExpect(model().attributeExists("message"));
    }

//    @Test
//    public void testUpdateUser() throws Exception {
//        User user = getTestUser();
//
//        when(userRepository.save(getTestUser())).thenReturn(getTestUser());
//        assertEquals(getTestUser(), userRepository.save(getTestUser()));
//
//        mockMvc.perform(post("/auth/updateUser")
//                        .param("email", user.getEmail())
//                        .param("password", user.getPassword())
//                        .param("name", user.getName())
//                        .param("surname", user.getSurname())
//                        .param("role", user.getRole().toString())
//                        .param("status", user.getStatus().toString())
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/auth/users"));
//
//    }
//
//    @Test
//    public void testUpdateUserId() throws Exception {
//        User user = getTestUser();
//        int userId=22;
//
//        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user)); // idk
//
//        // Perform GET request
//        mockMvc.perform(get("/auth/updateUser/1"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("/updateUser"))
//                .andExpect(model().attribute("user", user));
//    }

//////////////////////////////////////////////////////////////////////////////////////////////////////
//    @Test
//    public void testAddSpecialist() throws Exception {
//        User user = getTestUser();
//
//
//        mockMvc.perform(post("/auth/process_register_specialist")
//                        .param("email", user.getEmail())
//                        .param("password", user.getPassword())
//                        .param("name", user.getName())
//                        .param("surname", user.getSurname())
//                        .param("role", user.getRole().toString())
//                        .param("status", user.getStatus().toString())
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
//                .andExpect(status().isOk())
//                .andExpect(view().name("/login"))
//                .andExpect(model().attributeExists("message"));
//
//        User savedUser = userService.findByEnterEmail(user.getEmail());
//        assertNotNull(savedUser);
//
//        VerificationToken verificationToken = verificationTokenService.findByUser(savedUser);
//        assertNotNull(verificationToken);
//
//
//        GreenMailUtil.sendTextEmailTest("Schmetterling111111111111111@gmail.com", "from@localhost", "subject", "body");
//        MimeMessage[] message = greenMail.getReceivedMessages();
//        assertEquals(1, message.length);
//        assertEquals("subject", message[0].getSubject());
//        assertEquals(user.getEmail(), message[0].getRecipients(Message.RecipientType.TO)[0].toString());
//
////        verify(userService, times(1)).findByEnterEmail(user.getEmail());
////        verify(userService, times(1)).saveClient(user);
////        verify(verificationTokenService, times(1)).save(user, anyString());
////        verify(emailService, times(1)).sendHtmlMail(user);
//    }
//
//    @Test
//    public void testActivation() throws Exception {
//
//        User user = getTestUser();
//        Account account = new Account();
//// set account properties here
//        accountService.saveAccount(account);
//        user.setAccountIdaccount(account);
//
//        Seller seller = new Seller();
//        sellerService.saveSeller(seller);
//        user.setSellerIdSeller(seller);
//
//        userService.saveUser(user);
//
//        // create a verification token for the user
//        String token = UUID.randomUUID().toString();
//        verificationTokenService.save(user, token);
//
//        // send GET request to activation endpoint with the token parameter
//        mockMvc.perform(get("/activation")
//                        .param("token", token))
//                .andExpect(status().isOk())
//                .andExpect(view().name("activation"))
//                .andExpect(model().attribute("message", "Your account is successfully activated"));
//
//        // check that the user's status has been set to ACTIVE
//        User activatedUser = userService.findByEnterEmail("testuser");
//        assertEquals(activatedUser.getStatus(),(Status.ACTIVE));
//
//        // check that the verification token has been deleted
//        VerificationToken deletedToken = verificationTokenService.findByToken(token);
//        assertNull(deletedToken);
//
//        // check that the activation email has been sent
//        MimeMessage[] message = greenMail.getReceivedMessages();
//        assertEquals(1, message.length);
//        assertEquals("Your account is successfully activated", message[0].getSubject());
//
//    }


    private User getTestUser() {
        User user = new User();
        user.setId(22);
        user.setName("Schmetterling");
        user.setSurname("Schmetterling");
        user.setEmail("Schmetterling111111111111111@gmail.com");
        user.setPassword("Schmetterling");
        user.setStatus(Status.ACTIVE);
        user.setRole(Role.CLIENT);
        user.setPicture("Schmetterling");
        user.setAccountIdaccount(new Account());
        user.setSellerIdSeller(new Seller());
        return user;
    }

}