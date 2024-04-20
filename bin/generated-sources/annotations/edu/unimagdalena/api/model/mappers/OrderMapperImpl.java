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
import edu.unimagdalena.api.model.enums.PaymentMethod;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-19T19:42:04-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240325-1403, environment: Java 17.0.10 (Eclipse Adoptium)"
)
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order orderDtoToOrder(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.customer( orderDTO.customer() );
        order.id( orderDTO.id() );
        order.items( orderItemDTOListToOrderItemList( orderDTO.items() ) );
        if ( orderDTO.orderDate() != null ) {
            order.orderDate( LocalDateTime.parse( orderDTO.orderDate() ) );
        }
        order.payment( orderDTO.payment() );
        order.shipmentDetails( orderDTO.shipmentDetails() );
        order.status( orderDTO.status() );

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
        order.items( orderItemDTOListToOrderItemList( orderToSaveDto.items() ) );
        order.orderDate( orderToSaveDto.orderDate() );
        order.payment( paymentDTOToPayment( orderToSaveDto.payment() ) );
        order.shipmentDetails( shipmentDetailsDTOToShipmentDetails( orderToSaveDto.shipmentDetails() ) );
        order.status( orderToSaveDto.status() );

        return order.build();
    }

    @Override
    public OrderToSaveDto orderToOrderToSaveDto(Order order) {
        if ( order == null ) {
            return null;
        }

        CustomerDTO customer = null;
        LocalDateTime orderDate = null;
        OrderStatus status = null;
        List<OrderItemDTO> items = null;
        PaymentDTO payment = null;
        ShipmentDetailsDTO shipmentDetails = null;

        customer = customerToCustomerDTO( order.getCustomer() );
        orderDate = order.getOrderDate();
        status = order.getStatus();
        items = orderItemListToOrderItemDTOList( order.getItems() );
        payment = paymentToPaymentDTO( order.getPayment() );
        shipmentDetails = shipmentDetailsToShipmentDetailsDTO( order.getShipmentDetails() );

        OrderToSaveDto orderToSaveDto = new OrderToSaveDto( customer, orderDate, status, items, payment, shipmentDetails );

        return orderToSaveDto;
    }

    protected OrderItem orderItemDTOToOrderItem(OrderItemDTO orderItemDTO) {
        if ( orderItemDTO == null ) {
            return null;
        }

        OrderItem.OrderItemBuilder orderItem = OrderItem.builder();

        orderItem.amount( orderItemDTO.amount() );
        orderItem.id( orderItemDTO.id() );
        orderItem.order( orderItemDTO.order() );
        orderItem.product( orderItemDTO.product() );
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

        customer.address( customerDTO.address() );
        customer.email( customerDTO.email() );
        customer.id( customerDTO.id() );
        customer.name( customerDTO.name() );
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
        payment.paymentDate( paymentDTO.paymentDate() );
        payment.paymentMethod( paymentDTO.paymentMethod() );
        payment.totalPayment( paymentDTO.totalPayment() );

        return payment.build();
    }

    protected ShipmentDetails shipmentDetailsDTOToShipmentDetails(ShipmentDetailsDTO shipmentDetailsDTO) {
        if ( shipmentDetailsDTO == null ) {
            return null;
        }

        ShipmentDetails.ShipmentDetailsBuilder shipmentDetails = ShipmentDetails.builder();

        shipmentDetails.guideNumber( shipmentDetailsDTO.guideNumber() );
        shipmentDetails.id( shipmentDetailsDTO.id() );
        shipmentDetails.order( shipmentDetailsDTO.order() );
        shipmentDetails.shipmentAddress( shipmentDetailsDTO.shipmentAddress() );
        shipmentDetails.transporter( shipmentDetailsDTO.transporter() );

        return shipmentDetails.build();
    }

    protected List<OrderDTO> orderListToOrderDTOList(List<Order> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderDTO> list1 = new ArrayList<OrderDTO>( list.size() );
        for ( Order order : list ) {
            list1.add( orderToOrderDto( order ) );
        }

        return list1;
    }

    protected CustomerDTO customerToCustomerDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String email = null;
        String address = null;
        List<OrderDTO> orders = null;

        id = customer.getId();
        name = customer.getName();
        email = customer.getEmail();
        address = customer.getAddress();
        orders = orderListToOrderDTOList( customer.getOrders() );

        CustomerDTO customerDTO = new CustomerDTO( id, name, email, address, orders );

        return customerDTO;
    }

    protected PaymentDTO paymentToPaymentDTO(Payment payment) {
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

    protected ShipmentDetailsDTO shipmentDetailsToShipmentDetailsDTO(ShipmentDetails shipmentDetails) {
        if ( shipmentDetails == null ) {
            return null;
        }

        Long id = null;
        Order order = null;
        String shipmentAddress = null;
        String transporter = null;
        Long guideNumber = null;

        id = shipmentDetails.getId();
        order = shipmentDetails.getOrder();
        shipmentAddress = shipmentDetails.getShipmentAddress();
        transporter = shipmentDetails.getTransporter();
        guideNumber = shipmentDetails.getGuideNumber();

        ShipmentDetailsDTO shipmentDetailsDTO = new ShipmentDetailsDTO( id, order, shipmentAddress, transporter, guideNumber );

        return shipmentDetailsDTO;
    }
}
