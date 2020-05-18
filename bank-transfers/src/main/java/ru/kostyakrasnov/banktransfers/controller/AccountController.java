package ru.kostyakrasnov.banktransfers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kostyakrasnov.banktransfers.exception.BadRequestException;
import ru.kostyakrasnov.banktransfers.mapper.AccountMapper;
import ru.kostyakrasnov.banktransfers.model.DTO.AccountDTO;
import ru.kostyakrasnov.banktransfers.service.AccountService;

import java.util.List;

/**
 * author: kostyakrasnov
 * date: 13.05.2020
 */

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/account")
public class AccountController {

    private final AccountService service;
    private final AccountMapper accountMapper;

    @PutMapping("/transfer/fromId/{fromId}/toId/{toId}/money/{money}")
    public List<AccountDTO> transfer(@PathVariable Long fromId,
                         @PathVariable Long toId,
                         @PathVariable Double money){

        if (money <= 0)
            throw new BadRequestException("The amount cannot be negative");

        return accountMapper.domainListToListDto(service.transferMoney(fromId, toId, money));
    }

    @PutMapping("/withdraw/{id}/money/{money}")
    public AccountDTO withdraw(@PathVariable Long id,
                                    @PathVariable Double money){
        if (money <= 0)
            throw new BadRequestException("The amount cannot be negative");

        return accountMapper.domainToDto(service.withdrawMoney(id,money));

    }

    @PutMapping("/replenish/{id}/money/{money}")
    public AccountDTO replenish(@PathVariable Long id,
                               @PathVariable Double money){

        if (money <= 0)
            throw new BadRequestException("The amount cannot be negative");

        return accountMapper.domainToDto(service.replenishMoney(id,money));
    }


}
