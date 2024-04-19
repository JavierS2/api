package edu.unimagdalena.api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.unimagdalena.api.model.entities.Payment;
import edu.unimagdalena.api.model.enums.PaymentMethod;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("SELECT p FROM Payment p WHERE p.order.id = ?1")
    Payment findByOrderId(Long orderId);

    @Query("SELECT p FROM Payment p WHERE p.order.orderDate BETWEEN ?1 AND ?2")
    List<Payment> findBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT p FROM Payment p WHERE p.order.id = ?1 AND p.paymentMethod = ?2")
    List<Payment> findByOrderIdAndPaymentMethod(Long orderId, PaymentMethod paymentMethod);
}
