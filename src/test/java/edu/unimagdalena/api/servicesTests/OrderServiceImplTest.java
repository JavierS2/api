package edu.unimagdalena.api.servicesTests;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import edu.unimagdalena.api.model.dto_save.OrderToSaveDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import edu.unimagdalena.api.model.entities.Customer;
import edu.unimagdalena.api.model.entities.Order;
import edu.unimagdalena.api.model.dto.OrderDTO;
import edu.unimagdalena.api.model.enums.OrderStatus;
import edu.unimagdalena.api.model.mappers.OrderMapper;
import edu.unimagdalena.api.repository.CustomerRepository;
import edu.unimagdalena.api.repository.OrderRepository;
import edu.unimagdalena.api.service.implementations.OrderServiceImpl;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class OrderServiceImplTest {

    @Mock
    OrderRepository orderRepository;

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    OrderServiceImpl orderService;

    Customer customer1 = Customer.builder()
            .id(1L)
            .name("pepe")
            .email("pepe@example.co")
            .address("1234")
            .orders(null)
            .build();

    Order order1 = Order.builder()
            .id(2L)
            .customer(customer1)
            .orderDate(LocalDateTime.now())
            .status(OrderStatus.SENT)
            .build();

    Order order2 = Order.builder()
            .id(3L)
            .customer(customer1)
            .orderDate(LocalDateTime.now())
            .status(OrderStatus.PENDING)
            .build();

    OrderToSaveDto orderToSaveDto = OrderMapper.INSTANCE.orderToOrderToSaveDto(order1);
    OrderToSaveDto orderToSaveDto1 = OrderMapper.INSTANCE.orderToOrderToSaveDto(order2);

    @BeforeEach
    void setUp() {
        when(orderRepository.save(any())).thenReturn(order1);
        when(orderRepository.findById(anyLong())).thenReturn(Optional.ofNullable(order1));
        when(customerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(customer1));
    }

    @Test
    void testCreate() {
        // when
        OrderDTO saved = orderService.create(orderToSaveDto);
        // then
        assertThat(saved).isNotNull();
        assertThat(saved.id()).isEqualTo(2L);
    }

    @Test
    void testDelete() {
        // when
        OrderDTO saved = orderService.create(orderToSaveDto);
        when(orderRepository.count()).thenReturn(1L);
        assertEquals(1L, orderRepository.count());
        // then
        orderService.delete(saved.id());
        when(orderRepository.count()).thenReturn(0L);
        assertEquals(0L, orderRepository.count());
    }

    @Test
    void testGetAllOrders() {
        // when
        orderService.create(orderToSaveDto);
        orderService.create(orderToSaveDto);
        when(orderRepository.findAll()).thenReturn(List.of(order1, order2));
        assertThat(orderService.getAllOrders().size()).isEqualTo(2);
    }

    @Test
    void testGetBetweenDates() {
        // when
        orderService.create(orderToSaveDto);
        when(orderRepository.findBetweenDates(any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(List.of(order1));
        List<OrderDTO> res = orderService.getBetweenDates(LocalDateTime.now().minusDays(1),
                LocalDateTime.now().plusDays(1));
        // then
        assertNotNull(res);
        assertThat(res.size()).isEqualTo(1);
    }

    @Test
    void testGetByCustomerIdAndStatus() {
        // given
        OrderDTO saved = orderService.create(orderToSaveDto);
        when(orderRepository.findByCustomerIdAndStatus(anyLong(), any(OrderStatus.class))).thenReturn(List.of(order1));
        // when
        List<OrderDTO> res = orderService.getByCustomerIdAndStatus(saved.customer().id(), OrderStatus.SENT);
        // then
        assertThat(res.size()).isEqualTo(1);
    }

    @Test
    void testGetOrderById() {
        // given
        OrderDTO saved = orderService.create(orderToSaveDto);
        when(orderRepository.findById(saved.id())).thenReturn(Optional.ofNullable(order1));
        // when
        OrderDTO res = orderService.getOrderById(saved.id());
        // then
        assertEquals(saved.id(), res.id());
    }

    @Test
    void testUpdate() {
        // given
        orderService.create(orderToSaveDto);
        // when
        OrderDTO orderUpdate = orderService.update(orderToSaveDto1, 1L);
        // then
        assertEquals(OrderStatus.PENDING, orderUpdate.status());
    }
}
