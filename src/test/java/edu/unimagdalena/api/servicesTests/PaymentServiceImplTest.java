package edu.unimagdalena.api.servicesTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import edu.unimagdalena.api.model.dto_save.PaymentToSaveDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import edu.unimagdalena.api.model.entities.Order;
import edu.unimagdalena.api.model.entities.Payment;
import edu.unimagdalena.api.model.dto.PaymentDTO;
import edu.unimagdalena.api.model.enums.OrderStatus;
import edu.unimagdalena.api.model.enums.PaymentMethod;
import edu.unimagdalena.api.model.mappers.PaymentMapper;
import edu.unimagdalena.api.repository.OrderRepository;
import edu.unimagdalena.api.repository.PaymentRepository;
import edu.unimagdalena.api.service.implementations.PaymentServiceImpl;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PaymentServiceImplTest {

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    PaymentServiceImpl paymentService;

    Order order1 = Order.builder()
                .id(1L)
                .customer(null)
                .orderDate(LocalDateTime.now())
                .status(OrderStatus.SENT)
                .build();

    Payment payment1 = Payment.builder()
                .id(2L)
                .order(order1)
                .paymentDate(LocalDateTime.now())
                .totalPayment(50f)
                .paymentMethod(PaymentMethod.CASH)
                .build();

    Payment payment2 = Payment.builder()
                .id(3L)
                .order(order1)
                .paymentDate(LocalDateTime.now())
                .totalPayment(50f)
                .paymentMethod(PaymentMethod.NEQUI)
                .build();

    PaymentToSaveDto paymentToSaveDto = PaymentMapper.INSTANCE.paymentToPaymentToSaveDto(payment1);
    PaymentToSaveDto paymentToSaveDto1 = PaymentMapper.INSTANCE.paymentToPaymentToSaveDto(payment2);

    @BeforeEach
    void setUp(){
        when(paymentRepository.save(any())).thenReturn(payment1);
        when(paymentRepository.findAll()).thenReturn(List.of(payment1));
        when(paymentRepository.findById(anyLong())).thenReturn(Optional.ofNullable(payment1));
        when(orderRepository.findById(anyLong())).thenReturn(Optional.ofNullable(order1));
    }

    @Test
    void testCreate() {
        //given
        PaymentDTO saved = paymentService.create(paymentToSaveDto);
        //then
        assertThat(saved).isNotNull();
        assertEquals(2L, saved.id());
    }

    @Test
    void testDelete() {
        //when
        PaymentDTO saved = paymentService.create(paymentToSaveDto);
        when(paymentRepository.count()).thenReturn(2L);
        assertEquals(2L, paymentRepository.count());
        //then
        paymentService.delete(saved.id());
        when(paymentRepository.count()).thenReturn(0L);
        assertEquals(0L, paymentRepository.count());
    }

    @Test
    void testGetAllPayments() {
        //given
        paymentService.create(paymentToSaveDto);
        //when
        List<PaymentDTO> res = paymentService.getAllPayments();
        //then
        assertEquals(1, res.size());
    }

    @Test
    void testGetByOrderIdAndPaymentMethod() {
        //given
        PaymentDTO saved = paymentService.create(paymentToSaveDto);
        when(paymentRepository.findByOrderIdAndPaymentMethod(anyLong(), any(PaymentMethod.class))).thenReturn(List.of(payment1));
        //when
        List<PaymentDTO> res = paymentService.getByOrderIdAndPaymentMethod(order1.getId(), saved.paymentMethod());
        //then
        assertNotNull(res);
        assertEquals(2L, res.get(0).id());
    }

    @Test
    void testGetPaymentById() {
        //given
        PaymentDTO saved = paymentService.create(paymentToSaveDto);
        when(paymentRepository.findById(saved.id())).thenReturn(Optional.ofNullable(payment1));
        //when
        PaymentDTO res = paymentService.getPaymentById(saved.id());
        //then
        assertEquals(saved.id(), res.id());
    }

    @Test
    void testGetPaymentByOrderId() {
        //when
        paymentService.create(paymentToSaveDto);
        when(paymentRepository.findByOrderId(anyLong())).thenReturn(payment1);
        PaymentDTO payment = paymentService.getPaymentByOrderId(order1.getId());
        //then
        assertNotNull(payment);
        assertEquals(1L, payment.order().getId());
    }

    @Test
    void testGetPaymentsBetweenDates() {
        //when
        paymentService.create(paymentToSaveDto);
        when(paymentRepository.findByOrderDateBetweenDates(any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(List.of(payment1));
        List<PaymentDTO> res = paymentService.getPaymentsBetweenDates(LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1));
        //then
        assertNotNull(res);
        assertThat(res.size()).isEqualTo(1);
    }

    @Test
    void testUpdate() {
        //given
        paymentService.create(paymentToSaveDto);
        //when
        PaymentDTO paymentUpdate = paymentService.update(paymentToSaveDto1, 2L);
        //then
        assertEquals(PaymentMethod.NEQUI, paymentUpdate.paymentMethod());
    }
}
