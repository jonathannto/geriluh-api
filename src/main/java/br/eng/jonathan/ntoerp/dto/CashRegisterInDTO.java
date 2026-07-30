package br.eng.jonathan.ntoerp.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CashRegisterInDTO {

    @NotNull(message = "{CASH_REGISTER.INITIAL_BALANCE_NOT_NULL}")
    @DecimalMin(value = "0.00", message = "{CASH_REGISTER.INITIAL_BALANCE_MIN}")
    @Digits(integer = 10, fraction = 2, message = "{CASH_REGISTER.INITIAL_BALANCE_DIGITS}")
    private BigDecimal initialBalance;

    private String notes;

    @NotNull(message = "{CASH_REGISTER.USER_ID_NOT_NULL}")
    private Long userId;
}