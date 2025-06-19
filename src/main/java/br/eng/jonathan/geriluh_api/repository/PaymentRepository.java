package br.eng.jonathan.geriluh_api.repository;

import br.eng.jonathan.geriluh_api.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentRepository extends JpaRepository<Payment, Long> {
}