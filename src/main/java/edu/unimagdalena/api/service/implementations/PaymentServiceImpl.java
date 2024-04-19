package edu.unimagdalena.api.service.implementations;

import java.time.LocalDateTime;
import java.util.List;

import edu.unimagdalena.api.model.dto_save.PaymentToSaveDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unimagdalena.api.model.entities.Payment;
import edu.unimagdalena.api.model.dto.PaymentDTO;
import edu.unimagdalena.api.model.enums.PaymentMethod;
import edu.unimagdalena.api.exceptions.NotAbleToDeleteException;
import edu.unimagdalena.api.exceptions.ObjectNotFoundException;
import edu.unimagdalena.api.model.mappers.PaymentMapper;
import edu.unimagdalena.api.repository.PaymentRepository;
import edu.unimagdalena.api.service.services.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentDTO create(PaymentToSaveDto paymentToSaveDto) {
        Payment paymentSaved = paymentRepository.save(PaymentMapper.INSTANCE.paymentToSaveDtoToPayment(paymentToSaveDto));
        return PaymentMapper.INSTANCE.paymentToPaymentDto(paymentSaved);
    }

    @Override
    public PaymentDTO getPaymentById(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ObjectNotFoundException("Payment not found"));
        return PaymentMapper.INSTANCE.paymentToPaymentDto(payment);
    }

    @Override
    public PaymentDTO update(PaymentToSaveDto paymentToSaveDto, Long paymentId) {
        Payment paymentInDb = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ObjectNotFoundException("Payment not found"));
        paymentInDb.setTotalPayment(paymentToSaveDto.totalPayment());
        paymentInDb.setPaymentDate(paymentToSaveDto.paymentDate());
        paymentInDb.setPaymentMethod(paymentToSaveDto.paymentMethod());
        return PaymentMapper.INSTANCE.paymentToPaymentDto(paymentRepository.save(paymentInDb));
    }

    @Override
    public void delete(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new NotAbleToDeleteException("Payment not found, not able to delete"));
        paymentRepository.delete(payment);
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream()
                .map(PaymentMapper.INSTANCE::paymentToPaymentDto)
                .toList();
    }

    @Override
    public PaymentDTO getPaymentByOrderId(Long orderId) {
        Payment payment = paymentRepository.findByOrderId(orderId);
        return PaymentMapper.INSTANCE.paymentToPaymentDto(payment);
    }

    @Override
    public List<PaymentDTO> getPaymentsBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        List<Payment> payments = paymentRepository.findBetweenDates(startDate, endDate);
        return payments.stream()
                .map(PaymentMapper.INSTANCE::paymentToPaymentDto)
                .toList();
    }

    @Override
    public List<PaymentDTO> getByOrderIdAndPaymentMethod(Long orderId, PaymentMethod paymentMethod) {
        List<Payment> payments = paymentRepository.findByOrderIdAndPaymentMethod(orderId, paymentMethod);
        return payments.stream().map(PaymentMapper.INSTANCE::paymentToPaymentDto).toList();
    }
}
