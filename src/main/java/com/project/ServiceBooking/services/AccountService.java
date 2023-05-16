package com.project.ServiceBooking.services;

import com.project.ServiceBooking.data.Account;
import com.project.ServiceBooking.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account saveAccount(Account account){
        return accountRepository.save(account);
    }
}
