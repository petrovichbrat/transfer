package ru.kostyakrasnov.banktransfers.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kostyakrasnov.banktransfers.model.Account;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Test
    void transferMoney(){
        accountService.transferMoney(1L, 2L, 10000.00);

        Account account1 = accountService.findByIdPessimistic(1L);
        Account account2 = accountService.findByIdPessimistic(2L);
        BigDecimal a = account1.getBalance().add(account2.getBalance());
        assertEquals(a,BigDecimal.valueOf(2000000.00));

    }
    @Test
    void replenishMoney(){
        BigDecimal beforeAccount = accountService.findByIdPessimistic(1L).getBalance();
        Account afterAccount = accountService.replenishMoney(1L, 10000.00);
        assertEquals(afterAccount.getBalance(),beforeAccount.add(BigDecimal.valueOf(10000.00)));
    }
    @Test
    void withdrawMoney(){
        BigDecimal beforeAccount = accountService.findByIdPessimistic(1L).getBalance();
        Account afterAccount =  accountService.withdrawMoney(1L, 10000.00);
        assertEquals(afterAccount.getBalance(),beforeAccount.subtract(BigDecimal.valueOf(10000.00)));
    }

}