package edu.unimagdalena.api.service.implementations;

import java.time.LocalDateTime;
import java.util.List;

import edu.unimagdalena.api.model.dto_save.OrderToSaveDto;
import org.springframework.stereotype.Service;

import edu.unimagdalena.api.model.entities.Order;
import edu.unimagdalena.api.model.dto.OrderDTO;
import edu.unimagdalena.api.model.enums.OrderStatus;
import edu.unimagdalena.api.exceptions.NotAbleToDeleteException;
import edu.unimagdalena.api.exceptions.ObjectNotFoundException;
import edu.unimagdalena.api.model.mappers.OrderMapper;
import edu.unimagdalena.api.repository.OrderRepository;
import edu.unimagdalena.api.service.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDTO create(OrderToSaveDto orderToSaveDto) {
        Order orderSaved = orderRepository.save(OrderMapper.INSTANCE.orderToSaveDtoToOrder(orderToSaveDto));
        return OrderMapper.INSTANCE.orderToOrderDto(orderSaved);
    }

    @Override
    public OrderDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ObjectNotFoundException("Order not found"));
        return OrderMapper.INSTANCE.orderToOrderDto(order);
    }

    // Update only status
    @Override
    public OrderDTO update(OrderToSaveDto orderToSaveDto, Long orderId) {
        Order orderInDb = orderRepository.findById(orderId)
                .orElseThrow(() -> new ObjectNotFoundException("Order not found"));
        orderInDb.setStatus(orderToSaveDto.status());
        return OrderMapper.INSTANCE.orderToOrderDto(orderRepository.save(orderInDb));
    }

    @Override
    public void delete(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotAbleToDeleteException("Order not found, not able to delete"));
        orderRepository.delete(order);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> customers = orderRepository.findAll();
        return customers.stream()
                .map(OrderMapper.INSTANCE::orderToOrderDto)
                .toList();
    }

    @Override
    public List<OrderDTO> getOrdersByCustomerId(Long customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        return orders.stream().map(OrderMapper.INSTANCE::orderToOrderDto).toList();
    }

    @Override
    public List<OrderDTO> getBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        List<Order> orders = orderRepository.findBetweenDates(startDate, endDate);
        return orders.stream().map(OrderMapper.INSTANCE::orderToOrderDto).toList();
    }

    @Override
    public List<OrderDTO> getByCustomerIdAndStatus(Long customerId, OrderStatus status) {
        List<Order> orders = orderRepository.findByCustomerIdAndStatus(customerId, status);
        return orders.stream().map(OrderMapper.INSTANCE::orderToOrderDto).toList();
    }
}
