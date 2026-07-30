package br.eng.jonathan.ntoerp.dto.mapper;

import br.eng.jonathan.ntoerp.dto.CashRegisterDTO;
import br.eng.jonathan.ntoerp.dto.CashRegisterInDTO;
import br.eng.jonathan.ntoerp.dto.CashRegisterOutDTO;
import br.eng.jonathan.ntoerp.model.CashRegister;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CashRegisterMapper {

    @Mapping(target = "user", ignore = true)
    CashRegister toEntity(CashRegisterDTO cashRegisterDTO);

    @Mapping(target = "userId", source = "user.userId")
    CashRegisterDTO toDTO(CashRegister cashRegister);

    @Mapping(target = "cashRegisterId", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateEntityFromDto(CashRegisterDTO dto, @MappingTarget CashRegister entity);

    @Mapping(target = "user.userId", source = "userId")
    @Mapping(target = "cashRegisterId", ignore = true)
    @Mapping(target = "initDate", ignore = true)
    @Mapping(target = "endDate", ignore = true)
    @Mapping(target = "endBalance", ignore = true)
    @Mapping(target = "totalSales", ignore = true)
    @Mapping(target = "totalWithdrawals", ignore = true)
    @Mapping(target = "status", ignore = true)
    CashRegister toEntity(CashRegisterInDTO inDto);

    @Mapping(target = "userId", source = "user.userId")
    CashRegisterOutDTO toOpenOutDto(CashRegister entity);
}