package br.eng.jonathan.geriluh_api.service;

import br.eng.jonathan.geriluh_api.dto.PaymentTypeDTO;
import br.eng.jonathan.geriluh_api.exception_handler.exceptions.NotFoundException;
import br.eng.jonathan.geriluh_api.model.PaymentType;
import br.eng.jonathan.geriluh_api.repository.PaymentTypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class PaymentTypeService {

    private static final String PAYMENT_TYPE_SEARCH_ERROR = "PAYMENT_TYPE.SEARCH_ERROR";

    @Autowired
    private PaymentTypeRepository repository;

    @Autowired
    private MessageSource messageSource;

    public Page<PaymentType> listAllPaymentType(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public PaymentType findPaymentTypeById(Long paymentId) {
        return repository.findById(paymentId).orElseThrow(() -> new NotFoundException(getErrorMessage()));
    }

    public PaymentType createPaymentType(PaymentType paymentType) {
        return repository.save(paymentType);
    }

    public PaymentType updatePaymentType(Long paymentId, PaymentTypeDTO paymentTypeDTO) {

        var paymentType = repository.findById(paymentId)
                        .orElseThrow(() -> new NotFoundException(getErrorMessage()));

        BeanUtils.copyProperties(paymentTypeDTO, paymentType, "paymentTypeId");
        return repository.save(paymentType);
    }

    public void deletePaymentType(Long paymentId) {
        if (!repository.existsById(paymentId)) {
            throw new NotFoundException(getErrorMessage());
        }
        repository.deleteById(paymentId);
    }

    private String getErrorMessage() {
        return messageSource.getMessage(PAYMENT_TYPE_SEARCH_ERROR, null, Locale.getDefault());
    }
}
