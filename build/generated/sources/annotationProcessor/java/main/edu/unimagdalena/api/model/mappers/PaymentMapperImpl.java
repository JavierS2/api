package edu.unimagdalena.api.model.mappers;

import edu.unimagdalena.api.model.dto.OrderDTO;
import edu.unimagdalena.api.model.dto.OrderItemDTO;
import edu.unimagdalena.api.model.dto.PaymentDTO;
import edu.unimagdalena.api.model.dto_save.PaymentToSaveDto;
import edu.unimagdalena.api.model.entities.Customer;
import edu.unimagdalena.api.model.entities.Order;
import edu.unimagdalena.api.model.entities.OrderItem;
import edu.unimagdalena.api.model.entities.Payment;
import edu.unimagdalena.api.model.entities.Product;
import edu.unimagdalena.api.model.entities.ShipmentDetails;
import edu.unimagdalena.api.model.enums.OrderStatus;
import edu.unimagdalena.api.model.enums.PaymentMethod;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-19T13:04:05-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
public class PaymentMapperImpl implements PaymentMapper {

    @Override
    public Payment paymentDtoToPayment(PaymentDTO paymentDTO) {
        if ( paymentDTO == null ) {
            return null;
        }

        Payment.PaymentBuilder payment = Payment.builder();

        payment.id( paymentDTO.id() );
        payment.order( paymentDTO.order() );
        payment.totalPayment( paymentDTO.totalPayment() );
        payment.paymentDate( paymentDTO.paymentDate() );
        payment.paymentMethod( paymentDTO.paymentMethod() );

        return payment.build();
    }

    @Override
    public PaymentDTO paymentToPaymentDto(Payment payment) {
        if ( payment == null ) {
            return null;
        }

        Long id = null;
        Order order = null;
        Float totalPayment = null;
        LocalDateTime paymentDate = null;
        PaymentMethod paymentMethod = null;

        id = payment.getId();
        order = payment.getOrder();
        totalPayment = payment.getTotalPayment();
        paymentDate = payment.getPaymentDate();
        paymentMethod = payment.getPaymentMethod();

        PaymentDTO paymentDTO = new PaymentDTO( id, order, totalPayment, paymentDate, paymentMethod );

        return paymentDTO;
    }

    @Override
    public Payment paymentToSaveDtoToPayment(PaymentToSaveDto paymentToSaveDto) {
        if ( paymentToSaveDto == null ) {
            return null;
        }

        Payment.PaymentBuilder payment = Payment.builder();

        payment.order( orderDTOToOrder( paymentToSaveDto.order() ) );
        payment.totalPayment( paymentToSaveDto.totalPayment() );
        payment.paymentDate( paymentToSaveDto.paymentDate() );
        payment.paymentMethod( paymentToSaveDto.paymentMethod() );

        return payment.build();
    }

    @Override
    public PaymentToSaveDto paymentToPaymentToSaveDto(Payment payment) {
        if ( payment == null ) {
            return null;
        }

        OrderDTO order = null;
        Float totalPayment = null;
        LocalDateTime paymentDate = null;
        PaymentMethod paymentMethod = null;

        order = orderToOrderDTO( payment.getOrder() );
        totalPayment = payment.getTotalPayment();
        paymentDate = payment.getPaymentDate();
        paymentMethod = payment.getPaymentMethod();

        PaymentToSaveDto paymentToSaveDto = new PaymentToSaveDto( order, totalPayment, paymentDate, paymentMethod );

        return paymentToSaveDto;
    }

    protected OrderItem orderItemDTOToOrderItem(OrderItemDTO orderItemDTO) {
        if ( orderItemDTO == null ) {
            return null;
        }

        OrderItem.OrderItemBuilder orderItem = OrderItem.builder();

        orderItem.id( orderItemDTO.id() );
        orderItem.order( orderItemDTO.order() );
        orderItem.product( orderItemDTO.product() );
        orderItem.amount( orderItemDTO.amount() );
        orderItem.unitPrice( orderItemDTO.unitPrice() );

        return orderItem.build();
    }

    protected List<OrderItem> orderItemDTOListToOrderItemList(List<OrderItemDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItem> list1 = new ArrayList<OrderItem>( list.size() );
        for ( OrderItemDTO orderItemDTO : list ) {
            list1.add( orderItemDTOToOrderItem( orderItemDTO ) );
        }

        return list1;
    }

    protected Order orderDTOToOrder(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.id( orderDTO.id() );
        order.customer( orderDTO.customer() );
        if ( orderDTO.orderDate() != null ) {
            order.orderDate( LocalDateTime.parse( orderDTO.orderDate() ) );
        }
        order.status( orderDTO.status() );
        order.items( orderItemDTOListToOrderItemList( orderDTO.items() ) );
        order.payment( orderDTO.payment() );
        order.shipmentDetails( orderDTO.shipmentDetails() );

        return order.build();
    }

    protected OrderItemDTO orderItemToOrderItemDTO(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        Long id = null;
        Order order = null;
        Product product = null;
        Integer amount = null;
        Float unitPrice = null;

        id = orderItem.getId();
        order = orderItem.getOrder();
        product = orderItem.getProduct();
        amount = orderItem.getAmount();
        unitPrice = orderItem.getUnitPrice();

        OrderItemDTO orderItemDTO = new OrderItemDTO( id, order, product, amount, unitPrice );

        return orderItemDTO;
    }

    protected List<OrderItemDTO> orderItemListToOrderItemDTOList(List<OrderItem> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItemDTO> list1 = new ArrayList<OrderItemDTO>( list.size() );
        for ( OrderItem orderItem : list ) {
            list1.add( orderItemToOrderItemDTO( orderItem ) );
        }

        return list1;
    }

    protected OrderDTO orderToOrderDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        Long id = null;
        Customer customer = null;
        String orderDate = null;
        OrderStatus status = null;
        List<OrderItemDTO> items = null;
        Payment payment = null;
        ShipmentDetails shipmentDetails = null;

        id = order.getId();
        customer = order.getCustomer();
        if ( order.getOrderDate() != null ) {
            orderDate = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( order.getOrderDate() );
        }
        status = order.getStatus();
        items = orderItemListToOrderItemDTOList( order.getItems() );
        payment = order.getPayment();
        shipmentDetails = order.getShipmentDetails();

        OrderDTO orderDTO = new OrderDTO( id, customer, orderDate, status, items, payment, shipmentDetails );

        return orderDTO;
    }
}
