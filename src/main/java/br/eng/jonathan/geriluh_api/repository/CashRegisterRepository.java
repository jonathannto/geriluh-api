package br.eng.jonathan.geriluh_api.repository;

import br.eng.jonathan.geriluh_api.model.CashRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CashRegisterRepository extends JpaRepository<CashRegister, Long>, JpaSpecificationExecutor<CashRegister> {
}
