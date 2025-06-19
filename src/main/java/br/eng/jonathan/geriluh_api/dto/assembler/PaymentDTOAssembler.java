package br.eng.jonathan.geriluh_api.dto.assembler;

import br.eng.jonathan.geriluh_api.controller.PaymentController;
import br.eng.jonathan.geriluh_api.dto.PaymentDTO;
import br.eng.jonathan.geriluh_api.exception_handler.exceptions.NotFoundException;
import br.eng.jonathan.geriluh_api.model.Payment;
import br.eng.jonathan.geriluh_api.model.PaymentType;
import br.eng.jonathan.geriluh_api.service.PaymentService;
import br.eng.jonathan.geriluh_api.service.PaymentTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PaymentDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentTypeService paymentTypeService;

    public PaymentDTO mapToDTO(Payment payment) {
        return modelMapper.map(payment, PaymentDTO.class);
    }

    public Payment mapToEntity(PaymentDTO paymentDTO) throws NotFoundException {
        Payment payment = modelMapper.map(paymentDTO, Payment.class);

        if (paymentDTO.getPaymentId() != null) {
            Payment previousPayment = paymentService.findPaymentById(paymentDTO.getPaymentId());
            payment.setPaymentId(previousPayment.getPaymentId());
            payment.setInitialDatePay(paymentDTO.getInitialDatePay() != null ? paymentDTO.getInitialDatePay() : previousPayment.getInitialDatePay());
            payment.setFinalDatePay(paymentDTO.getFinalDatePay() != null ? paymentDTO.getFinalDatePay() : previousPayment.getFinalDatePay());
            payment.setTotalPay(paymentDTO.getTotalPay() != null ? paymentDTO.getTotalPay() : previousPayment.getTotalPay());
            payment.setStatusPay(paymentDTO.getStatusPay() != null ? paymentDTO.getStatusPay() : previousPayment.getStatusPay());
        }

        if (paymentDTO.getPaymentTypeId() != null) {
            PaymentType paymentType = paymentTypeService.findPaymentTypeById(paymentDTO.getPaymentTypeId());
            payment.setPaymentType(paymentType);
        }

        return payment;
    }

    /**
     * Converte uma entidade em um DTO e adiciona links HATEOAS.
     * @param payment {@link br.eng.jonathan.geriluh_api.model.Payment}
     * @return {@link EntityModel} <{@link br.eng.jonathan.geriluh_api.dto.PaymentDTO}>
     */
    public EntityModel<PaymentDTO> mapToEntityModelDTO(Payment payment) {
        return EntityModel.of(mapToDTO(payment),
                linkTo(methodOn(PaymentController.class)
                        .getPaymentById(payment.getPaymentId()))
                        .withSelfRel()
                        .withType("GET"),
                linkTo(methodOn(PaymentController.class)
                        .list(null))
                        .withRel("list")
                        .withType("GET"),
                linkTo(methodOn(PaymentController.class)
                        .createNewPayment(null, null))
                        .withRel("create")
                        .withType("POST"),
                linkTo(methodOn(PaymentController.class)
                        .updatePayment(payment.getPaymentId(), null))
                        .withRel("update")
                        .withType("PUT"),
                linkTo(methodOn(PaymentController.class)
                        .deletePayment(payment.getPaymentId()))
                        .withRel("delete")
                        .withType("DELETE")
        );
    }
}
