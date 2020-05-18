package ru.kostyakrasnov.banktransfers.mapper;

import org.mapstruct.Mapper;
import ru.kostyakrasnov.banktransfers.model.Account;
import ru.kostyakrasnov.banktransfers.model.DTO.AccountDTO;

import java.util.List;

/**
 * author: kostyakrasnov
 * date: 17.05.2020
 */

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDTO domainToDto (Account in);

    List<AccountDTO> domainListToListDto(List<Account> in);
}
