package edu.unimagdalena.api.repositoriesTests;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import edu.unimagdalena.api.AbstractIntegrationDBTest;
import edu.unimagdalena.api.model.entities.Customer;
import edu.unimagdalena.api.model.entities.Order;
import edu.unimagdalena.api.model.enums.OrderStatus;
import edu.unimagdalena.api.repository.CustomerRepository;
import edu.unimagdalena.api.repository.OrderRepository;

class OrderRepositoryTest extends AbstractIntegrationDBTest{

    OrderRepository orderRepository;

    CustomerRepository customerRepository;

    @Autowired
    public OrderRepositoryTest(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    Customer customer1 = Customer.builder()
            .name("pepe")
            .email("pepe@example.co")
            .address("1234")
            .orders(null)
            .build();

    Order order1 = Order.builder()
                .customer(null)
                .orderDate(LocalDateTime.now())
                .status(OrderStatus.SENT)
                .items(null)
                .payment(null)
                .shipmentDetalis(null)
                .build();

    Order order2 = Order.builder()
                .customer(null)
                .orderDate(LocalDateTime.now())
                .status(OrderStatus.PENDING)
                .items(null)
                .payment(null)
                .shipmentDetalis(null)
                .build();


    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
    }

    @Test
    @DisplayName("test save")
    void givenAnOrder_WhenSave_ThenOrderWithId(){
        //given
        Order orderSaved = orderRepository.save(order1);
        //then
        assertThat(orderSaved.getId()).isNotNull();
    }

    @Test
    @DisplayName("test read")
    void givenOrders_ThenGetAll(){
        //given
        orderRepository.save(order1);
        orderRepository.save(order2);
        List<Order> orders = orderRepository.findAll();
        //then
        assertThat(orders.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("test delete")
    void givenIdOrder_thenDeleteIt(){
        //given
        orderRepository.save(order1);
        Long id = order1.getId();
        //when
        orderRepository.deleteById(id);
        //then
        assertThat(orderRepository.findById(id)).isEmpty();
    }

    @Test
    @DisplayName("test update")
    void givenAnOrder_ThenUpdateThis(){
        //given
        Long id = orderRepository.save(order2).getId();
        OrderStatus newStatus = OrderStatus.DELIVERED;
        //when
        Order order = orderRepository.findById(id).orElseThrow(null);
        if(order!= null){
            order.setStatus(newStatus);
            Order orderUpdate = orderRepository.save(order);
            //then
            assertThat(orderUpdate.getStatus()).isEqualTo(newStatus);
        }
    }

    @Test
    @DisplayName("test findBetweenDates")
    void givenOrder_ThenfindBetweenDates(){
        //given
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);
        orderRepository.save(order1);
        orderRepository.save(order2);
        //when
        List<Order> ordersInDates = orderRepository.findBetweenDates(startDate, endDate);
        //then
        assertThat(ordersInDates.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("test findByCustomerId")
    void givenCustomerId_ThenFindOrders(){
        //given
        Long id = customer1.getId();
        customerRepository.save(customer1);
        order1.setCustomer(customer1);
        orderRepository.save(order1);
        //when
        List<Order> orders = orderRepository.findByCustomerId(id);
        //then
        assertThat(orders.size()).isEqualTo(1);
    }


    @Test
    @DisplayName("test findByCustomerIdAndStatus")
    void givenOrders_ThenFindByCustomerIdAndStatus(){
        //given
        Long id = customer1.getId();
        customerRepository.save(customer1);
        order1.setCustomer(customer1);
        orderRepository.save(order1);
        List<Order> orders = orderRepository.findByCustomerIdAndStatus(id, OrderStatus.SENT);
        //then
        assertThat(orders.size()).isEqualTo(1);
    }


}