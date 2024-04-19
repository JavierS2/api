package edu.unimagdalena.api.controller;

import edu.unimagdalena.api.model.dto.PaymentDTO;
import edu.unimagdalena.api.exceptions.NotAbleToDeleteException;
import edu.unimagdalena.api.exceptions.ObjectNotFoundException;
import edu.unimagdalena.api.model.dto_save.PaymentToSaveDto;
import edu.unimagdalena.api.service.services.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @RequestMapping("")
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        List<PaymentDTO> payments = paymentService.getAllPayments();
        return ResponseEntity.ok().body(payments);
    }

    @RequestMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable("id") Long id) {
        try {
            PaymentDTO paymentDTO = paymentService.getPaymentById(id);
            return ResponseEntity.ok().body(paymentDTO);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/order/{orderId}")
    public ResponseEntity<PaymentDTO> getPaymentsByOrderId(@PathVariable("orderId") Long orderId) {
        PaymentDTO payment = paymentService.getPaymentByOrderId(orderId);
        return ResponseEntity.ok(payment);
    }

    @RequestMapping("date-range?startDate={startDate}&endDate={endDate}")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByDateRange(@PathVariable("startDate") LocalDateTime startDate, @PathVariable("endDate") LocalDateTime endDate) {
        List<PaymentDTO> payments = paymentService.getPaymentsBetweenDates(startDate, endDate);
        return ResponseEntity.ok().body(payments);
    }

    @PostMapping("")
    public ResponseEntity<PaymentDTO> create(@RequestBody PaymentToSaveDto paymentToSaveDto) {
        try {
            PaymentDTO payment = paymentService.create(paymentToSaveDto);
            return ResponseEntity.status(201).body(payment);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> update(@PathVariable("id") Long id, @Valid @RequestBody PaymentToSaveDto paymentToSaveDto) {
        try {
            PaymentDTO payment = paymentService.update(paymentToSaveDto, id);
            return ResponseEntity.ok().body(payment);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        try {
            paymentService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NotAbleToDeleteException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
