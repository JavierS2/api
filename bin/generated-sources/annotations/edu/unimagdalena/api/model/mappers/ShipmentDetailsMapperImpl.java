package edu.unimagdalena.api.model.mappers;

import edu.unimagdalena.api.model.dto.OrderDTO;
import edu.unimagdalena.api.model.dto.OrderItemDTO;
import edu.unimagdalena.api.model.dto.ShipmentDetailsDTO;
import edu.unimagdalena.api.model.dto_save.ShipmentDetailsToSaveDto;
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
    date = "2024-04-19T19:42:03-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240325-1403, environment: Java 17.0.10 (Eclipse Adoptium)"
)
public class ShipmentDetailsMapperImpl implements ShipmentDetailsMapper {

    @Override
    public ShipmentDetails shipmentDetailsDtoToShipmentDetails(ShipmentDetailsDTO shipmentDetailsDTO) {
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

    @Override
    public ShipmentDetailsDTO shipmentDetailsToShipmentDetailsDto(ShipmentDetails shipmentDetails) {
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

    @Override
    public ShipmentDetails shipmentDetailsToSaveDtoToShipmentDetails(ShipmentDetailsToSaveDto shipmentDetailsToSaveDto) {
        if ( shipmentDetailsToSaveDto == null ) {
            return null;
        }

        ShipmentDetails.ShipmentDetailsBuilder shipmentDetails = ShipmentDetails.builder();

        shipmentDetails.guideNumber( shipmentDetailsToSaveDto.guideNumber() );
        shipmentDetails.order( orderDTOToOrder( shipmentDetailsToSaveDto.order() ) );
        shipmentDetails.shipmentAddress( shipmentDetailsToSaveDto.shipmentAddress() );
        shipmentDetails.transporter( shipmentDetailsToSaveDto.transporter() );

        return shipmentDetails.build();
    }

    @Override
    public ShipmentDetailsToSaveDto shipmentDetailsToShipmentDetailsToSaveDto(ShipmentDetails shipmentDetails) {
        if ( shipmentDetails == null ) {
            return null;
        }

        OrderDTO order = null;
        String shipmentAddress = null;
        String transporter = null;
        Long guideNumber = null;

        order = orderToOrderDTO( shipmentDetails.getOrder() );
        shipmentAddress = shipmentDetails.getShipmentAddress();
        transporter = shipmentDetails.getTransporter();
        guideNumber = shipmentDetails.getGuideNumber();

        ShipmentDetailsToSaveDto shipmentDetailsToSaveDto = new ShipmentDetailsToSaveDto( order, shipmentAddress, transporter, guideNumber );

        return shipmentDetailsToSaveDto;
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

    protected Order orderDTOToOrder(OrderDTO orderDTO) {
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
