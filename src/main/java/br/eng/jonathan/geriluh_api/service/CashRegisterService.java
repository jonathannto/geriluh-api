package br.eng.jonathan.geriluh_api.service;

import br.eng.jonathan.geriluh_api.exception_handler.exceptions.NotFoundException;
import br.eng.jonathan.geriluh_api.model.CashRegister;
import br.eng.jonathan.geriluh_api.repository.CashRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CashRegisterService {

    private static final String CASH_REGISTER_SEARCH_ERRO = "CASH_REGISTER.SEARCH_ERROR";

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private CashRegisterRepository repository;

    public Page<CashRegister> listAllCashRegister(Pageable pagination) {
        return repository.findAll(pagination);
    }

    public CashRegister findCashRegisterById(Long userId) throws NotFoundException {
        return repository.findById(userId).orElseThrow(() -> new NotFoundException(getMessageErro()));
    }

    private String getMessageErro() {
        return messageSource.getMessage(CashRegisterService.CASH_REGISTER_SEARCH_ERRO, null, LocaleContextHolder.getLocale());
    }

}
