package edu.unimagdalena.api.model.mappers;

import edu.unimagdalena.api.model.dto.CustomerDTO;
import edu.unimagdalena.api.model.dto.OrderDTO;
import edu.unimagdalena.api.model.dto.OrderItemDTO;
import edu.unimagdalena.api.model.dto.PaymentDTO;
import edu.unimagdalena.api.model.dto.ShipmentDetailsDTO;
import edu.unimagdalena.api.model.dto_save.OrderToSaveDto;
import edu.unimagdalena.api.model.entities.Customer;
import edu.unimagdalena.api.model.entities.Order;
import edu.unimagdalena.api.model.entities.OrderItem;
import edu.unimagdalena.api.model.entities.Payment;
import edu.unimagdalena.api.model.entities.Product;
import edu.unimagdalena.api.model.entities.ShipmentDetails;
import edu.unimagdalena.api.model.enums.OrderStatus;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-18T21:10:16-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.2 (Oracle Corporation)"
)
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order orderDtoToOrder(OrderDTO orderDTO) {
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

    @Override
    public OrderDTO orderToOrderDto(Order order) {
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

    @Override
    public Order orderToSaveDtoToOrder(OrderToSaveDto orderToSaveDto) {
        if ( orderToSaveDto == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.customer( customerDTOToCustomer( orderToSaveDto.customer() ) );
        order.orderDate( orderToSaveDto.orderDate() );
        order.status( orderToSaveDto.status() );
        order.items( orderItemDTOListToOrderItemList( orderToSaveDto.items() ) );
        order.payment( paymentDTOToPayment( orderToSaveDto.payment() ) );
        order.shipmentDetails( shipmentDetailsDTOToShipmentDetails( orderToSaveDto.shipmentDetails() ) );

        return order.build();
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

    protected List<Order> orderDTOListToOrderList(List<OrderDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Order> list1 = new ArrayList<Order>( list.size() );
        for ( OrderDTO orderDTO : list ) {
            list1.add( orderDtoToOrder( orderDTO ) );
        }

        return list1;
    }

    protected Customer customerDTOToCustomer(CustomerDTO customerDTO) {
        if ( customerDTO == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.id( customerDTO.id() );
        customer.name( customerDTO.name() );
        customer.email( customerDTO.email() );
        customer.address( customerDTO.address() );
        customer.orders( orderDTOListToOrderList( customerDTO.orders() ) );

        return customer.build();
    }

    protected Payment paymentDTOToPayment(PaymentDTO paymentDTO) {
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

    protected ShipmentDetails shipmentDetailsDTOToShipmentDetails(ShipmentDetailsDTO shipmentDetailsDTO) {
        if ( shipmentDetailsDTO == null ) {
            return null;
        }

        ShipmentDetails.ShipmentDetailsBuilder shipmentDetails = ShipmentDetails.builder();

        shipmentDetails.id( shipmentDetailsDTO.id() );
        shipmentDetails.order( shipmentDetailsDTO.order() );
        shipmentDetails.shipmentAddress( shipmentDetailsDTO.shipmentAddress() );
        shipmentDetails.transporter( shipmentDetailsDTO.transporter() );
        shipmentDetails.guideNumber( shipmentDetailsDTO.guideNumber() );

        return shipmentDetails.build();
    }
}
