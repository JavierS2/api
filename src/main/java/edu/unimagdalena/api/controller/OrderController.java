package edu.unimagdalena.api.controller;

import edu.unimagdalena.api.model.dto.OrderDTO;
import edu.unimagdalena.api.exceptions.NotAbleToDeleteException;
import edu.unimagdalena.api.exceptions.ObjectNotFoundException;
import edu.unimagdalena.api.model.dto_save.OrderToSaveDto;
import edu.unimagdalena.api.service.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("id") Long id) {
        try {
            OrderDTO orderDTO = orderService.getOrderById(id);
            return ResponseEntity.ok().body(orderDTO);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByCustomerId(@PathVariable("customerId") Long customerId) {
        try {
            List<OrderDTO> orders = orderService.getOrdersByCustomerId(customerId);
            return ResponseEntity.ok().body(orders);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/date-range?startDate={startDate}&endDate={endDate}")
    public ResponseEntity<List<OrderDTO>> getOrdersByDateRange(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        List<OrderDTO> orders = orderService.getBetweenDates(startDate, endDate);
        return ResponseEntity.ok().body(orders);
    }

    @PostMapping("")
    public ResponseEntity<OrderDTO> create(@Valid @RequestBody OrderToSaveDto orderToSaveDto) {
        OrderDTO order = orderService.create(orderToSaveDto);
        return ResponseEntity.status(201).body(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> update(@PathVariable("id") Long id,@Valid @RequestBody OrderToSaveDto orderToSaveDto) {
        try {
            OrderDTO order = orderService.update(orderToSaveDto, id);
            return ResponseEntity.ok().body(order);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        try {
            orderService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NotAbleToDeleteException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
