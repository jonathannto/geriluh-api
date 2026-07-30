package br.eng.jonathan.ntoerp.repository;

import br.eng.jonathan.ntoerp.model.CashRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CashRegisterRepository extends JpaRepository<CashRegister, Long>, JpaSpecificationExecutor<CashRegister> {

    Optional<CashRegister> findByUserUserIdAndStatus(Long userId, String status);

    boolean existsByUserUserIdAndStatusIgnoreCase(Long userId, String status);

}
