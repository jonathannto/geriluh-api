package br.eng.jonathan.ntoerp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Relation(collectionRelation = "cashRegisters", itemRelation = "cashRegister")
public class CashRegisterOutDTO extends RepresentationModel<CashRegisterOutDTO> {

    private Long cashRegisterId;
    private OffsetDateTime initDate;
    private OffsetDateTime endDate;
    private BigDecimal initialBalance;
    private BigDecimal endBalance;
    private BigDecimal totalSales;
    private BigDecimal totalWithdrawals;
    private String status;
    private String notes;
    private Long userId;
}