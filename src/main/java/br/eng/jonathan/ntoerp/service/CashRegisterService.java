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

    private static final String MSG_SEARCH_ERROR = "CASH_REGISTER.SEARCH_ERROR";
    private static final String MSG_USER_HAS_ACTIVE_REGISTER = "CASH_REGISTER.USER_HAS_ACTIVE_REGISTER";
    private static final String MSG_ALREADY_CLOSED = "CASH_REGISTER.ALREADY_CLOSED";

    private final MessageSource messageSource;
    private final CashRegisterMapper cashRegisterMapper;
    private final CashRegisterRepository repository;

    public Page<CashRegister> listAllCashRegister(Pageable pagination) {
        return repository.findAll(pagination);
    }

    public CashRegister findCashRegisterById(Long userId) throws NotFoundException {
        return repository.findById(userId).orElseThrow(() -> new NotFoundException(getMessage(MSG_SEARCH_ERROR)));
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
            throw new BusinessException(getMessage(MSG_USER_HAS_ACTIVE_REGISTER));
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
                .orElseThrow(() -> new NotFoundException(getMessage(MSG_SEARCH_ERROR)));

        if ("closed".equalsIgnoreCase(cashRegister.getStatus())) {
            throw new BusinessException(getMessage(MSG_ALREADY_CLOSED));
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
            throw new NotFoundException(getMessage(MSG_SEARCH_ERROR));
        }
        repository.deleteById(cashRegisterId);
    }

    private String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

}
