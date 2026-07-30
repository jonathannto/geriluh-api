package br.eng.jonathan.ntoerp.service;

import br.eng.jonathan.ntoerp.dto.CashRegisterDTO;
import br.eng.jonathan.ntoerp.dto.CashRegisterInDTO;
import br.eng.jonathan.ntoerp.dto.mapper.CashRegisterMapper;
import br.eng.jonathan.ntoerp.exception_handler.exceptions.BusinessException;
import br.eng.jonathan.ntoerp.exception_handler.exceptions.NotFoundException;
import br.eng.jonathan.ntoerp.model.CashRegister;
import br.eng.jonathan.ntoerp.repository.CashRegisterRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class CashRegisterService {

    private static final String CASH_REGISTER_SEARCH_ERRO = "CASH_REGISTER.SEARCH_ERROR";

    private final MessageSource messageSource;
    private final CashRegisterMapper cashRegisterMapper;
    private final CashRegisterRepository repository;

    public Page<CashRegister> listAllCashRegister(Pageable pagination) {
        return repository.findAll(pagination);
    }

    public CashRegister findCashRegisterById(Long userId) throws NotFoundException {
        return repository.findById(userId).orElseThrow(() -> new NotFoundException(getMessageErro()));
    }

    public CashRegister createCashRegister(CashRegister cashRegister) {
        return repository.save(cashRegister);
    }

    public CashRegister updateCashRegister(Long cashRegisterId, CashRegisterDTO cashRegisterDTO) {
        CashRegister cashRegister = findCashRegisterById(cashRegisterId);

        cashRegisterMapper.updateEntityFromDto(cashRegisterDTO, cashRegister);

        return repository.save(cashRegister);
    }

    @Transactional
    public CashRegister openCashRegister(CashRegisterInDTO inDto) {

        boolean hasOpenRegister = repository
                .existsByUserUserIdAndStatusIgnoreCase(inDto.getUserId(), "open");

        if (hasOpenRegister) {
            throw new BusinessException("User already has an open cash register.");
        }

        CashRegister entity = cashRegisterMapper.toEntity(inDto);
        entity.setInitDate(OffsetDateTime.now());
        entity.setStatus("open");

        if (entity.getInitialBalance() == null) {
            entity.setInitialBalance(BigDecimal.ZERO);
        }

        return repository.save(entity);
    }

    @Transactional
    public CashRegister closeCashRegister(Long cashRegisterId, BigDecimal endBalance, String notes) {
        CashRegister cashRegister = repository.findById(cashRegisterId)
                .orElseThrow(() -> new NotFoundException("Cash register not found with ID: " + cashRegisterId));

        if ("closed".equalsIgnoreCase(cashRegister.getStatus())) {
            throw new BusinessException("Cash register is already closed.");
        }

        cashRegister.setEndDate(OffsetDateTime.now());
        cashRegister.setStatus("closed");
        cashRegister.setEndBalance(endBalance);

        if (notes != null) {
            cashRegister.setNotes(notes);
        }

        return repository.save(cashRegister);
    }

    public void deleteCashRegister(Long cashRegisterId) {
        if (!repository.existsById(cashRegisterId)) {
            throw new NotFoundException("CashRegister not found");
        }
        repository.deleteById(cashRegisterId);
    }

    private String getMessageErro() {
        return messageSource.getMessage(CashRegisterService.CASH_REGISTER_SEARCH_ERRO, null, LocaleContextHolder.getLocale());
    }

}
