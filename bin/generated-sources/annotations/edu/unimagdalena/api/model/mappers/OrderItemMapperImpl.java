package edu.unimagdalena.api.model.mappers;

import edu.unimagdalena.api.model.dto.CustomerDTO;
import edu.unimagdalena.api.model.dto.OrderDTO;
import edu.unimagdalena.api.model.dto.OrderItemDTO;
import edu.unimagdalena.api.model.dto.PaymentDTO;
import edu.unimagdalena.api.model.dto.ProductDTO;
import edu.unimagdalena.api.model.dto.ShipmentDetailsDTO;
import edu.unimagdalena.api.model.dto_save.OrderItemToSaveDto;
import edu.unimagdalena.api.model.entities.Customer;
import edu.unimagdalena.api.model.entities.Order;
import edu.unimagdalena.api.model.entities.OrderItem;
import edu.unimagdalena.api.model.entities.Payment;
import edu.unimagdalena.api.model.entities.Product;
import edu.unimagdalena.api.model.entities.ShipmentDetails;
import edu.unimagdalena.api.model.enums.OrderStatus;
import edu.unimagdalena.api.model.enums.PaymentMethod;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-20T13:25:46-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240325-1403, environment: Java 17.0.10 (Eclipse Adoptium)"
)
public class OrderItemMapperImpl implements OrderItemMapper {

    @Override
    public OrderItemDTO orderItemToOrderItemDto(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        Long id = null;
        OrderDTO order = null;
        ProductDTO product = null;
        Integer amount = null;
        Float unitPrice = null;

        id = orderItem.getId();
        order = orderToOrderDTO( orderItem.getOrder() );
        product = productToProductDTO( orderItem.getProduct() );
        amount = orderItem.getAmount();
        unitPrice = orderItem.getUnitPrice();

        OrderItemDTO orderItemDTO = new OrderItemDTO( id, order, product, amount, unitPrice );

        return orderItemDTO;
    }

    @Override
    public OrderItem orderItemDtoToOrderItem(OrderItemDTO orderItemDTO) {
        if ( orderItemDTO == null ) {
            return null;
        }

        OrderItem.OrderItemBuilder orderItem = OrderItem.builder();

        orderItem.amount( orderItemDTO.amount() );
        orderItem.id( orderItemDTO.id() );
        orderItem.order( orderDTOToOrder( orderItemDTO.order() ) );
        orderItem.product( productDTOToProduct( orderItemDTO.product() ) );
        orderItem.unitPrice( orderItemDTO.unitPrice() );

        return orderItem.build();
    }

    @Override
    public OrderItem orderItemToSaveDtoToOrderItem(OrderItemToSaveDto orderItemToSaveDto) {
        if ( orderItemToSaveDto == null ) {
            return null;
        }

        OrderItem.OrderItemBuilder orderItem = OrderItem.builder();

        orderItem.amount( orderItemToSaveDto.amount() );
        orderItem.order( orderDTOToOrder( orderItemToSaveDto.order() ) );
        orderItem.product( productDTOToProduct1( orderItemToSaveDto.product() ) );
        orderItem.unitPrice( orderItemToSaveDto.unitPrice() );

        return orderItem.build();
    }

    @Override
    public OrderItemToSaveDto orderItemToOrderItemToSaveDto(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderDTO order = null;
        ProductDTO product = null;
        Integer amount = null;
        Float unitPrice = null;

        order = orderToOrderDTO( orderItem.getOrder() );
        product = productToProductDTO( orderItem.getProduct() );
        amount = orderItem.getAmount();
        unitPrice = orderItem.getUnitPrice();

        OrderItemToSaveDto orderItemToSaveDto = new OrderItemToSaveDto( order, product, amount, unitPrice );

        return orderItemToSaveDto;
    }

    protected CustomerDTO customerToCustomerDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String email = null;
        String address = null;

        id = customer.getId();
        name = customer.getName();
        email = customer.getEmail();
        address = customer.getAddress();

        CustomerDTO customerDTO = new CustomerDTO( id, name, email, address );

        return customerDTO;
    }

    protected List<OrderItemDTO> orderItemListToOrderItemDTOList(List<OrderItem> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItemDTO> list1 = new ArrayList<OrderItemDTO>( list.size() );
        for ( OrderItem orderItem : list ) {
            list1.add( orderItemToOrderItemDto( orderItem ) );
        }

        return list1;
    }

    protected PaymentDTO paymentToPaymentDTO(Payment payment) {
        if ( payment == null ) {
            return null;
        }

        Long id = null;
        OrderDTO order = null;
        Float totalPayment = null;
        LocalDateTime paymentDate = null;
        PaymentMethod paymentMethod = null;

        id = payment.getId();
        order = orderToOrderDTO( payment.getOrder() );
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
        OrderDTO order = null;
        String shipmentAddress = null;
        String transporter = null;
        Long guideNumber = null;

        id = shipmentDetails.getId();
        order = orderToOrderDTO( shipmentDetails.getOrder() );
        shipmentAddress = shipmentDetails.getShipmentAddress();
        transporter = shipmentDetails.getTransporter();
        guideNumber = shipmentDetails.getGuideNumber();

        ShipmentDetailsDTO shipmentDetailsDTO = new ShipmentDetailsDTO( id, order, shipmentAddress, transporter, guideNumber );

        return shipmentDetailsDTO;
    }

    protected OrderDTO orderToOrderDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        Long id = null;
        CustomerDTO customer = null;
        LocalDateTime orderDate = null;
        OrderStatus status = null;
        List<OrderItemDTO> items = null;
        PaymentDTO payment = null;
        ShipmentDetailsDTO shipmentDetails = null;

        id = order.getId();
        customer = customerToCustomerDTO( order.getCustomer() );
        orderDate = order.getOrderDate();
        status = order.getStatus();
        items = orderItemListToOrderItemDTOList( order.getItems() );
        payment = paymentToPaymentDTO( order.getPayment() );
        shipmentDetails = shipmentDetailsToShipmentDetailsDTO( order.getShipmentDetails() );

        OrderDTO orderDTO = new OrderDTO( id, customer, orderDate, status, items, payment, shipmentDetails );

        return orderDTO;
    }

    protected ProductDTO productToProductDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        Float price = null;
        Integer stock = null;

        id = product.getId();
        name = product.getName();
        price = product.getPrice();
        stock = product.getStock();

        ProductDTO productDTO = new ProductDTO( id, name, price, stock );

        return productDTO;
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

        return customer.build();
    }

    protected List<OrderItem> orderItemDTOListToOrderItemList(List<OrderItemDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItem> list1 = new ArrayList<OrderItem>( list.size() );
        for ( OrderItemDTO orderItemDTO : list ) {
            list1.add( orderItemDtoToOrderItem( orderItemDTO ) );
        }

        return list1;
    }

    protected Payment paymentDTOToPayment(PaymentDTO paymentDTO) {
        if ( paymentDTO == null ) {
            return null;
        }

        Payment.PaymentBuilder payment = Payment.builder();

        payment.id( paymentDTO.id() );
        payment.order( orderDTOToOrder( paymentDTO.order() ) );
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
        shipmentDetails.order( orderDTOToOrder( shipmentDetailsDTO.order() ) );
        shipmentDetails.shipmentAddress( shipmentDetailsDTO.shipmentAddress() );
        shipmentDetails.transporter( shipmentDetailsDTO.transporter() );

        return shipmentDetails.build();
    }

    protected Order orderDTOToOrder(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.customer( customerDTOToCustomer( orderDTO.customer() ) );
        order.id( orderDTO.id() );
        order.items( orderItemDTOListToOrderItemList( orderDTO.items() ) );
        order.orderDate( orderDTO.orderDate() );
        order.payment( paymentDTOToPayment( orderDTO.payment() ) );
        order.shipmentDetails( shipmentDetailsDTOToShipmentDetails( orderDTO.shipmentDetails() ) );
        order.status( orderDTO.status() );

        return order.build();
    }

    protected Product productDTOToProduct(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id( productDTO.id() );
        product.name( productDTO.name() );
        product.price( productDTO.price() );
        product.stock( productDTO.stock() );

        return product.build();
    }

    protected Product productDTOToProduct1(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id( productDTO.id() );
        product.name( productDTO.name() );
        product.price( productDTO.price() );
        product.stock( productDTO.stock() );

        return product.build();
    }
}
