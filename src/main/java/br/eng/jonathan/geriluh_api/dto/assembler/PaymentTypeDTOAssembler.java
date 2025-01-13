package br.eng.jonathan.geriluh_api.dto.assembler;
import br.eng.jonathan.geriluh_api.controller.CashRegisterController;
import br.eng.jonathan.geriluh_api.dto.PaymentTypeDTO;
import br.eng.jonathan.geriluh_api.model.PaymentType;
import br.eng.jonathan.geriluh_api.service.PaymentTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PaymentTypeDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PaymentTypeService service;

    public PaymentTypeDTO mapToDTO(PaymentType paymentType) {
        return modelMapper.map(paymentType, PaymentTypeDTO.class);
    }

    public PaymentType mapToEntity(PaymentTypeDTO paymentTypeDTO) {

        PaymentType paymentType = modelMapper.map(paymentTypeDTO, PaymentType.class);

        if(paymentTypeDTO.getPaymentTypeId() != null) {
            PaymentType previousPaymentType = service.findPaymentTypeById(paymentTypeDTO.getPaymentTypeId());
            paymentType.setPaymentTypeId(previousPaymentType.getPaymentTypeId());
            paymentType.setName(paymentTypeDTO.getName() != null ? paymentTypeDTO.getName() : previousPaymentType.getName());
            paymentType.setDescription(paymentTypeDTO.getDescription() != null ? paymentTypeDTO.getDescription() : previousPaymentType.getDescription());
            paymentType.setTotal(paymentTypeDTO.getTotal() != null ? paymentTypeDTO.getTotal() : previousPaymentType.getTotal());
        }

        return paymentType;
    }

    /**
     * Uses {@link ModelMapper} to convert an entity into a DTO
     * while also transforming the class into an {@link EntityModel} and adding links for HATEOAS
     * @author Jonathan
     * @since 1.0
     * @serialData 2025-01-13
     * @param paymentType {@link br.eng.jonathan.geriluh_api.model.PaymentType}
     * @return {@link EntityModel} <{@link br.eng.jonathan.geriluh_api.dto.PaymentTypeDTO}>
     */
    public EntityModel<PaymentTypeDTO> mapToEntityModelDTO(PaymentType paymentType) {
        return EntityModel.of(mapToDTO(paymentType),
                linkTo(methodOn(CashRegisterController.class)
                        .getCashRegisterById(paymentType.getPaymentTypeId()))
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
                        .updateCashRegister(paymentType.getPaymentTypeId(), null))
                        .withRel("update")
                        .withType("PUT"),
                linkTo(methodOn(CashRegisterController.class)
                        .deleteCashRegister(paymentType.getPaymentTypeId()))
                        .withRel("delete")
                        .withType("DELETE")
        );
    }

}
