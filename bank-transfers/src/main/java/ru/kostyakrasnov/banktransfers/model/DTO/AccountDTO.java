package ru.kostyakrasnov.banktransfers.model.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

/**
 * author: kostyakrasnov
 * date: 14.05.2020
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO {
    private Long id;
    private String name;
    private BigDecimal balance;
}
