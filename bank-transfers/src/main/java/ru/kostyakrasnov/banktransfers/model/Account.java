package ru.kostyakrasnov.banktransfers.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * author: kostyakrasnov
 * date: 13.05.2020
 */
@Data
@Entity
public class Account {
    @Id
    @GeneratedValue(generator = "ACCOUNT_SEQ_GENERATOR")
    @GenericGenerator(
            name = "ACCOUNT_SEQ_GENERATOR",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "ACCOUNT_SEQ"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;
    private String name;
    private BigDecimal balance;

}
