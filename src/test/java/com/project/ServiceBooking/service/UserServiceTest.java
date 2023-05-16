package com.project.ServiceBooking.service;

import com.project.ServiceBooking.data.*;
import com.project.ServiceBooking.repositories.UserRepository;
import com.project.ServiceBooking.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private int userId;

    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final UserService userService = new UserService(userRepository);

    @Test
    public void findByIdTest() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(getTestUser()));
        User user = userService.findById(userId);
        assertEquals(22, user.getId());
    }

    @Test
    public void findByIdTestFailure() {
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        User user = userService.findById(userId);
        assertNull(user);
    }

    @Test
    public void findAllTest() {
        when(userRepository.findAll()).thenReturn(getTestUserList());
        assertEquals(2, userService.findAll().size());
    }

    @Test
    public void saveUserTest(){
        when(userRepository.save(getTestUser())).thenReturn(getTestUser());
        assertEquals(getTestUser(), userService.saveUser(getTestUser()));
    }

    @Test
    public void testUserSaveClient() {
        User user = getTestUser();
        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userService.saveClient(user);

        assertEquals(user.getEmail(), savedUser.getEmail());
        assertEquals(user.getPassword(), savedUser.getPassword());
        assertEquals(Role.CLIENT, savedUser.getRole());
        assertEquals(Status.INACTIVE, savedUser.getStatus());
    }
    @Test
    public void testUserSpecialistClient() {
        User user = getTestUser();
        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userService.saveSpecialist(user);

        assertEquals(user.getEmail(), savedUser.getEmail());
        assertEquals(user.getPassword(), savedUser.getPassword());
        assertEquals(Role.SPECIALIST, savedUser.getRole());
        assertEquals(Status.INACTIVE, savedUser.getStatus());
    }

    @Test
    public void deleteByIdTest() {
        assertDoesNotThrow(() -> userService.deleteById(userId));
    }

    @Test
    public void findByEnterEmailTest() {
        when(userRepository.findByEnterEmail("Ruhm")).thenReturn(getTestUser());
        assertEquals(getTestUser(), userService.findByEnterEmail("Ruhm"));
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

    private List<User> getTestUserList() {
        List<User> userList = new ArrayList<>();
        userList.add(getTestUser());
        User user = getTestUser();
        user.setId(33);
        userList.add(user);
        return userList;
    }

}
