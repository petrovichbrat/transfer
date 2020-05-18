package ru.kostyakrasnov.banktransfers.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kostyakrasnov.banktransfers.exception.NegativeBalanceException;
import ru.kostyakrasnov.banktransfers.model.Account;
import ru.kostyakrasnov.banktransfers.repository.AccountRepository;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * author: kostyakrasnov
 * date: 13.05.2020
 */
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;
    private Logger logger = LoggerFactory.getLogger(AccountService.class);


    @Transactional(readOnly = true)
    public Account findByIdPessimistic(Long id){
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity with id {"+ id +"} not found"));
    }

    @Transactional
    public Account update(Account account){
        return repository.save(account);
    }


    @Transactional
    public Account replenishMoney(Long id, Double money){

        logger.info("Start put money for Account id {}" , id);
        Account account = findByIdPessimistic(id);

        if (account.getBalance().compareTo(BigDecimal.valueOf(money)) < 0)
            throw new NegativeBalanceException("Account with id {"+ account.getId() +"} insufficient funds");

        account.setBalance(account.getBalance().add(BigDecimal.valueOf(money)));

        account = update(account);
        logger.info("End put money for Account id {}" , id);

        return account;
    }


    @Transactional
    public List<Account> transferMoney(Long fromId, Long toId, Double money){

        logger.info("Start transfer money for Account from id {} and Account to id {}", fromId, toId);
        Account fromAccount = findByIdPessimistic(fromId);
        Account toAccount = findByIdPessimistic(toId);

        if (fromAccount.getBalance().compareTo(BigDecimal.valueOf(money)) < 0)
            throw new NegativeBalanceException("Account with id {"+ fromAccount.getId() +"} insufficient funds");

        fromAccount.setBalance(fromAccount.getBalance().subtract(BigDecimal.valueOf(money)));
        toAccount.setBalance(toAccount.getBalance().add(BigDecimal.valueOf(money)));

        fromAccount = update(fromAccount);
        toAccount = update(toAccount);


        logger.info("End transfer money for Account from id {} and Account to id {}", fromId, toId);

        return Stream.of(fromAccount,toAccount).collect(Collectors.toList());
    }
    @Transactional
    public Account withdrawMoney(Long id, Double money){

        logger.info("Start withdraw money for Account id {}", id);

        Account account = findByIdPessimistic(id);

        if (account.getBalance().compareTo(BigDecimal.valueOf(money)) < 0)
            throw new NegativeBalanceException("Account with id {"+ account.getId() +"} insufficient funds");

        account.setBalance(account.getBalance().subtract(BigDecimal.valueOf(money)));

        account = update(account);
        logger.info("End withdraw money for Account id {}", id);

        return account;
    }

}
