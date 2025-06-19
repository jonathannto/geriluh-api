package br.eng.jonathan.geriluh_api.service;

import br.eng.jonathan.geriluh_api.dto.PaymentDTO;
import br.eng.jonathan.geriluh_api.exception_handler.exceptions.NotFoundException;
import br.eng.jonathan.geriluh_api.model.Payment;
import br.eng.jonathan.geriluh_api.repository.PaymentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private static final String PAYMENT_FIND_PAYMENT_ERRO = "PAYMENT.SEARCH_ERROR";

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private PaymentRepository repository;

    public Page<Payment> listAllPayments(Pageable pagination) {
        return repository.findAll(pagination);
    }

    public Payment findPaymentById(Long paymentId) throws NotFoundException {
        return repository.findById(paymentId).orElseThrow(() -> new NotFoundException(getMessageErro()));
    }

    public Payment createPayment(Payment payment) throws NotFoundException {
        return repository.save(payment);
    }

    public Payment updatePayment(Long paymentId, PaymentDTO paymentDTO) {
        Payment existingPayment = repository.findById(paymentId).orElseThrow(() -> new NotFoundException(getMessageErro()));

        BeanUtils.copyProperties(paymentDTO, existingPayment, "paymentId");
        return repository.save(existingPayment);
    }

    public void deletePayment(Long paymentIdId) {
        if(!repository.existsById(paymentIdId)) {
            throw new NotFoundException(getMessageErro());
        }
        repository.deleteById(paymentIdId);
    }

    private String getMessageErro() {
        return messageSource.getMessage(PAYMENT_FIND_PAYMENT_ERRO, null, LocaleContextHolder.getLocale());
    }
}
