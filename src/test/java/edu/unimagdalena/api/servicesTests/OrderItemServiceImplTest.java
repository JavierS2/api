package edu.unimagdalena.api.servicesTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
import edu.unimagdalena.api.model.entities.OrderItem;
import edu.unimagdalena.api.model.entities.Product;
import edu.unimagdalena.api.model.dto.OrderItemDTO;
import edu.unimagdalena.api.model.enums.OrderStatus;
import edu.unimagdalena.api.model.mappers.OrderItemMapper;
import edu.unimagdalena.api.repository.CustomerRepository;
import edu.unimagdalena.api.repository.OrderItemRepository;
import edu.unimagdalena.api.repository.OrderRepository;
import edu.unimagdalena.api.repository.ProductRepository;
import edu.unimagdalena.api.service.implementations.OrderItemServiceImpl;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class OrderItemServiceImplTest {

    @Mock
    private OrderItemRepository orderItemRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private OrderItemServiceImpl orderItemService;

    Customer customer1 = Customer.builder()
            .id(1l)
            .name("pepe")
            .email("pepe@example.co")
            .address("1234")
            .build();

    Order order1 = Order.builder()
                .id(2l)
                .customer(customer1)
                .orderDate(LocalDateTime.now())
                .status(OrderStatus.SENT)
                .build();
    
    Product product1 = Product.builder()
            .id(3l)
            .name("sopa")
            .price((float) 5.0)
            .stock(200)
            .build();

    OrderItem orderItem1 = OrderItem.builder()
                .id(4l)
                .order(order1)
                .product(product1)
                .amount(4)
                .unitPrice((float) 5.0)
                .build();

    OrderItemDTO orderItemDTO = OrderItemMapper.INSTANCE.orderItemToOrderItemDto(orderItem1);

    @BeforeEach
    void setUp(){
        when(orderItemRepository.save(any())).thenReturn(orderItem1);
        when(orderItemRepository.findById(anyLong())).thenReturn(Optional.ofNullable(orderItem1));
        when(productRepository.findById(anyLong())).thenReturn(Optional.ofNullable(product1));
        when(orderRepository.findById(anyLong())).thenReturn(Optional.ofNullable(order1));
        when(customerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(customer1));
    }

    @Test
    void testCalculateTotalSalesForProduct() {
        //given
        when(orderItemRepository.calculateTotalSalesForProduct(anyString())).thenReturn(20f);
        //then
        assertThat(orderItemService.calculateTotalSalesForProduct("sopa")).isEqualTo(20f);
    }

    @Test
    void testCreate() {
        //when
        OrderItemDTO saved = orderItemService.create(orderItemDTO);
        //then
        assertThat(saved).isNotNull();
        assertThat(saved.id()).isEqualTo(4l);
    }

    @Test
    void testDelete() {
        //when
        OrderItemDTO saved = orderItemService.create(orderItemDTO);
        when(orderItemRepository.count()).thenReturn(4l);
        assertEquals(4L, orderItemRepository.count());
        //then
        orderItemService.delete(saved.id());
        when(orderItemRepository.count()).thenReturn(0L);
        assertEquals(0L, orderItemRepository.count());
    }

    @Test
    void testGetAllOrderItems() {
        //when
        orderItemService.create(orderItemDTO);
        orderItemService.create(orderItemDTO);
        when(orderItemRepository.findAll()).thenReturn(List.of(orderItem1));
        //then
        assertThat(orderItemService.getAllOrderItems().get(0).id()).isEqualTo(orderItem1.getId());
    }

    @Test
    void testGetOrderItemById() {
        //when
        orderItemService.create(orderItemDTO);
        when(orderItemRepository.findById(anyLong())).thenReturn(Optional.ofNullable(orderItem1));
        OrderItemDTO orderItemFind = orderItemService.getOrderItemById(4l);
        //then
        assertThat(orderItemFind.id()).isEqualTo(4l);
    }

    @Test
    void testGetOrderItemsByOrderId() {
        orderItemService.create(orderItemDTO);
        when(orderItemRepository.findByOrderId(anyLong())).thenReturn(List.of(orderItem1));
        List<OrderItemDTO> orderItemFind = orderItemService.getOrderItemsByOrderId(4l);
        //then
        assertThat(orderItemFind.size()).isEqualTo(1);
    }

    @Test
    void testGetOrderItemsByProductId() {
        orderItemService.create(orderItemDTO);
        when(orderItemRepository.findByProductId(anyLong())).thenReturn(List.of(orderItem1));
        List<OrderItemDTO> orderItemFind = orderItemService.getOrderItemsByProductId(4l);
        //then
        assertThat(orderItemFind.size()).isEqualTo(1);
    }

    @Test
    void testUpdate() {
        //when
        orderItemService.create(orderItemDTO);
        when(orderItemRepository.findById(anyLong())).thenReturn(Optional.ofNullable(orderItem1));
        OrderItemDTO orderItemFind = orderItemService.update(orderItemDTO, 4l);
        //then
        assertEquals(4l, orderItemFind.id());
    }
}
