package edu.unimagdalena.api.service.services;

import java.time.LocalDateTime;
import java.util.List;

import edu.unimagdalena.api.model.dto.PaymentDTO;
import edu.unimagdalena.api.model.dto_save.PaymentToSaveDto;
import edu.unimagdalena.api.model.enums.PaymentMethod;

public interface PaymentService {
    // CRUD
    PaymentDTO create(PaymentToSaveDto paymentToSaveDto);

    PaymentDTO getPaymentById(Long paymentId);

    PaymentDTO update(PaymentToSaveDto paymentToSaveDto, Long paymentId);

    void delete(Long paymentId);

    // Other methods

    List<PaymentDTO> getAllPayments();

   PaymentDTO getPaymentByOrderId(Long orderId);

    List<PaymentDTO> getPaymentsBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    List<PaymentDTO> getByOrderIdAndPaymentMethod(Long orderId, PaymentMethod paymentMethod);
}
