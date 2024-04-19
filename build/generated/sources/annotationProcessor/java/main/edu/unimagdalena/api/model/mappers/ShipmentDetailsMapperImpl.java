package edu.unimagdalena.api.model.mappers;

import edu.unimagdalena.api.model.dto.OrderDTO;
import edu.unimagdalena.api.model.dto.OrderItemDTO;
import edu.unimagdalena.api.model.dto.ShipmentDetailsDTO;
import edu.unimagdalena.api.model.dto_save.ShipmentDetailsToSaveDto;
import edu.unimagdalena.api.model.entities.Order;
import edu.unimagdalena.api.model.entities.OrderItem;
import edu.unimagdalena.api.model.entities.ShipmentDetails;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-18T21:48:16-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 17.0.2 (Oracle Corporation)"
)
public class ShipmentDetailsMapperImpl implements ShipmentDetailsMapper {

    @Override
    public ShipmentDetails shipmentDetailsDtoToShipmentDetails(ShipmentDetailsDTO shipmentDetailsDTO) {
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

        shipmentDetails.order( orderDTOToOrder( shipmentDetailsToSaveDto.order() ) );
        shipmentDetails.shipmentAddress( shipmentDetailsToSaveDto.shipmentAddress() );
        shipmentDetails.transporter( shipmentDetailsToSaveDto.transporter() );
        shipmentDetails.guideNumber( shipmentDetailsToSaveDto.guideNumber() );

        return shipmentDetails.build();
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
}