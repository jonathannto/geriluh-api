package br.eng.jonathan.geriluh_api.dto.assembler;

import br.eng.jonathan.geriluh_api.controller.CashRegisterController;
import br.eng.jonathan.geriluh_api.dto.CashRegisterDTO;
import br.eng.jonathan.geriluh_api.exception_handler.exceptions.NotFoundException;
import br.eng.jonathan.geriluh_api.model.CashRegister;
import br.eng.jonathan.geriluh_api.service.CashRegisterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class CashRegisterDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CashRegisterService service;

    public CashRegisterDTO mapToDTO(CashRegister cashRegister) {
        return modelMapper.map(cashRegister, CashRegisterDTO.class);
    }

    public CashRegister mapToEntity(CashRegisterDTO cashRegisterDTO) throws NotFoundException {
        CashRegister cashRegister = modelMapper.map(cashRegisterDTO, CashRegister.class);

        if (cashRegisterDTO.getCashRegisterId() != null) {
            CashRegister previousCashRegister = service.findCashRegisterById(cashRegisterDTO.getCashRegisterId());
            cashRegister.setCashRegisterId(previousCashRegister.getCashRegisterId());
            cashRegister.setInitDate(cashRegisterDTO.getInitDate() != null ? cashRegisterDTO.getInitDate() : previousCashRegister.getInitDate());
            cashRegister.setEndDate(cashRegisterDTO.getEndDate() != null ? cashRegisterDTO.getEndDate() : previousCashRegister.getEndDate());
            cashRegister.setInitialBalance(cashRegisterDTO.getInitialBalance() != null ? cashRegisterDTO.getInitialBalance() : previousCashRegister.getInitialBalance());
            cashRegister.setEndBalance(cashRegisterDTO.getEndBalance() != null ? cashRegisterDTO.getEndBalance() : previousCashRegister.getEndBalance());
            cashRegister.setTotalSales(cashRegisterDTO.getTotalSales() != null ? cashRegisterDTO.getTotalSales() : previousCashRegister.getTotalSales());
            cashRegister.setTotalWithdrawals(cashRegisterDTO.getTotalWithdrawals() != null ? cashRegisterDTO.getTotalWithdrawals() : previousCashRegister.getTotalWithdrawals());
            cashRegister.setStatus(cashRegisterDTO.getStatus() != null ? cashRegisterDTO.getStatus() : previousCashRegister.getStatus());
            cashRegister.setNotes(cashRegisterDTO.getNotes() != null ? cashRegisterDTO.getNotes() : previousCashRegister.getNotes());
            cashRegister.setUser(previousCashRegister.getUser()); // O campo `user` é normalmente mantido do registro anterior
        }
        return cashRegister;
    }

    /**
     * Uses {@link ModelMapper} to convert an entity into a DTO
     * while also transforming the class into an {@link EntityModel} and adding links for HATEOAS
     * @author Jonathan
     * @since 1.0
     * @serialData 2024-12-03
     * @param cashRegister {@link br.eng.jonathan.geriluh_api.model.CashRegister}
     * @return {@link EntityModel} <{@link br.eng.jonathan.geriluh_api.dto.CashRegisterDTO}>
     */
    public EntityModel<CashRegisterDTO> mapToEntityModelDTO(CashRegister cashRegister) {
        return EntityModel.of(mapToDTO(cashRegister),
                linkTo(methodOn(CashRegisterController.class)
                        .getCashRegisterById(cashRegister.getCashRegisterId()))
                        .withSelfRel()
                        .withType("GET"),
                linkTo(methodOn(CashRegisterController.class)
                        .list(null))
                        .withRel("list")
                        .withType("GET"),
                linkTo(methodOn(CashRegisterController.class)
                        .createNewCashRegister(null, null))
                        .withRel("create")
                        .withType("POST"),
                linkTo(methodOn(CashRegisterController.class)
                        .updateCashRegister(cashRegister.getCashRegisterId(), null))
                        .withRel("update")
                        .withType("PUT"),
                linkTo(methodOn(CashRegisterController.class)
                        .deleteCashRegister(cashRegister.getCashRegisterId()))
                        .withRel("delete")
                        .withType("DELETE")
        );
    }


}
