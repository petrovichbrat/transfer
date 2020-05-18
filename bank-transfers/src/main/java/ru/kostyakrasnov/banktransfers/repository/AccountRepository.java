package ru.kostyakrasnov.banktransfers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import ru.kostyakrasnov.banktransfers.model.Account;

import javax.persistence.LockModeType;
import java.util.Optional;

/**
 * author: kostyakrasnov
 * date: 13.05.2020
 */
@Repository

public interface AccountRepository extends JpaRepository<Account,Long> {


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Account> findById(Long id);

//    Optional<Account> getById(Long id);

}
