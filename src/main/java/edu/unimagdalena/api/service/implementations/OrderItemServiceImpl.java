package edu.unimagdalena.api.service.implementations;

import java.util.List;

import edu.unimagdalena.api.model.dto_save.OrderItemToSaveDto;
import org.springframework.stereotype.Service;

import edu.unimagdalena.api.model.entities.OrderItem;
import edu.unimagdalena.api.model.dto.OrderItemDTO;
import edu.unimagdalena.api.exceptions.NotAbleToDeleteException;
import edu.unimagdalena.api.exceptions.ObjectNotFoundException;
import edu.unimagdalena.api.model.mappers.OrderItemMapper;
import edu.unimagdalena.api.repository.OrderItemRepository;
import edu.unimagdalena.api.service.services.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, OrderItemMapper orderItemMapper) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public OrderItemDTO create(OrderItemToSaveDto orderItemToSaveDto) {
        OrderItem orderItemSaved = orderItemRepository.save(OrderItemMapper.INSTANCE.orderItemToSaveDtoToOrderItem(orderItemToSaveDto));
        return OrderItemMapper.INSTANCE.orderItemToOrderItemDto(orderItemSaved);
    }

    @Override
    public OrderItemDTO getOrderItemById(Long orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new ObjectNotFoundException("OrderItem not found"));
        return OrderItemMapper.INSTANCE.orderItemToOrderItemDto(orderItem);
    }

    @Override
    public OrderItemDTO update(OrderItemToSaveDto orderItemToSaveDto, Long orderItemId) {
        OrderItem orderItemInDb = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new ObjectNotFoundException("OrderItem not found"));
        orderItemInDb.setAmount(orderItemToSaveDto.amount());
        orderItemInDb.setUnitPrice(orderItemToSaveDto.unitPrice());
        return OrderItemMapper.INSTANCE.orderItemToOrderItemDto(orderItemRepository.save(orderItemInDb));
    }

    @Override
    public void delete(Long orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new NotAbleToDeleteException("OrderItem not found, not able to delete"));
        orderItemRepository.delete(orderItem);
    }

    @Override
    public List<OrderItemDTO> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        return orderItems.stream().map(OrderItemMapper.INSTANCE::orderItemToOrderItemDto).toList();
    }

    @Override
    public List<OrderItemDTO> getOrderItemsByOrderId(Long orderId) {
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);
        return orderItems.stream().map(OrderItemMapper.INSTANCE::orderItemToOrderItemDto).toList();
    }

    @Override
    public List<OrderItemDTO> getOrderItemsByProductId(Long productId) {
        List<OrderItem> orderItems = orderItemRepository.findByProductId(productId);
        return orderItems.stream().map(OrderItemMapper.INSTANCE::orderItemToOrderItemDto).toList();
    }

    @Override
    public Float calculateTotalSalesForProduct(String productName) {
        return orderItemRepository.calculateTotalSalesForProduct(productName);
    }

}

