package br.eng.jonathan.geriluh_api.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTypeDTO {

    private Long paymentTypeId;

    @NotBlank(message = "{PAYMENT_TYPE.NAME_NOT_BLANK}")
    @Size(max = 50, message = "{PAYMENT_TYPE.NAME_SIZE}")
    private String name;

    @Size(max = 45, message = "{PAYMENT_TYPE.DESCRIPTION_SIZE}")
    private String description;

    @NotNull(message = "{PAYMENT_TYPE.TOTAL_NOT_NULL}")
    @DecimalMin(value = "0.00", message = "{PAYMENT_TYPE.TOTAL_MIN}")
    @Digits(integer = 10, fraction = 2, message = "{PAYMENT_TYPE.TOTAL_DIGITS}")
    private BigDecimal total;
}