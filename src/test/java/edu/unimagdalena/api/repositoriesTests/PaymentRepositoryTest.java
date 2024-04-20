package edu.unimagdalena.api.repositoriesTests;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.unimagdalena.api.AbstractIntegrationDBTest;
import edu.unimagdalena.api.model.entities.Order;
import edu.unimagdalena.api.model.entities.Payment;
import edu.unimagdalena.api.model.enums.OrderStatus;
import edu.unimagdalena.api.model.enums.PaymentMethod;
import edu.unimagdalena.api.repository.OrderRepository;
import edu.unimagdalena.api.repository.PaymentRepository;

public class PaymentRepositoryTest extends AbstractIntegrationDBTest{
    PaymentRepository paymentRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    public PaymentRepositoryTest(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }

    Order order1 = Order.builder()
                .customer(null)
                .orderDate(LocalDateTime.now())
                .status(OrderStatus.SENT)
                .items(null)
                .payment(null)
                .shipmentDetails(null)
                .build();

    Payment payment1 = Payment.builder()
            .order(null)
            .totalPayment((float) 5000.0)
            .paymentDate(LocalDateTime.now())
            .paymentMethod(PaymentMethod.CASH)
            .build();

    Payment payment2 = Payment.builder()
            .order(null)
            .totalPayment((float) 6000.0)
            .paymentDate(LocalDateTime.now().minusDays(1))
            .paymentMethod(PaymentMethod.NEQUI)
            .build();

    @BeforeEach
    void setUp(){
        paymentRepository.deleteAll();
    }

    @Test
    @DisplayName("test save")
    void givenAnPayment_WhenSave_ThenPaymentWithId(){
        //given
        Payment paymentSaved = paymentRepository.save(payment1);
        //then
        assertThat(paymentSaved.getId()).isNotNull();
    }

    @Test
    @DisplayName("test read")
    void givenPayment_ThenGetAll(){
        //given
        paymentRepository.save(payment1);
        paymentRepository.save(payment2);
        List<Payment> payments = paymentRepository.findAll();
        //then
        assertThat(payments.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("test delete")
    void givenIdPayment_thenDeleteIt(){
        //given
        paymentRepository.save(payment1);
        Long id = payment1.getId();
        //when
        paymentRepository.deleteById(id);
        //then
        assertThat(paymentRepository.findById(id)).isEmpty();
    }

    @Test
    @DisplayName("test update")
    void givenAnPayment_ThenUpdateThis(){
        //given
        Long id = paymentRepository.save(payment2).getId();
        PaymentMethod newPaymentMethod = PaymentMethod.DAVIPLATA;
        //when
        Payment payment = paymentRepository.findById(id).orElseThrow(null);
        if(payment!= null){
            payment.setPaymentMethod(newPaymentMethod);
            Payment paymentUpdate = paymentRepository.save(payment);
            //then
            assertThat(paymentUpdate.getPaymentMethod()).isEqualTo(newPaymentMethod);
        }
    }

    @Test
    @DisplayName("test findByOrderDateBetweenDates")
    void givenPayment_ThenfindByOrderDateBetweenDates(){
        //given
        Order orderSaved = orderRepository.save(order1);
        payment1.setOrder(orderSaved);
        LocalDateTime startDate = LocalDateTime.now().minusDays(2);
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);
        paymentRepository.save(payment1);
        //when
        List<Payment> paymentsInDates = paymentRepository.findByOrderDateBetweenDates(startDate, endDate);
        //then
        assertThat(paymentsInDates.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("test findByCustomerIdAndStatus")
    void givenPayment_ThenSelectByOrderIdAndPaymentMethod(){
        //given
        Order orderSaved = orderRepository.save(order1);
        payment1.setOrder(order1);
        paymentRepository.save(payment1);
        List<Payment> payments = paymentRepository.findByOrderIdAndPaymentMethod(orderSaved.getId(), PaymentMethod.CASH);
        //then
        assertThat(payments.size()).isEqualTo(1);
    }
    
}
