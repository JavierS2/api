package edu.unimagdalena.api.model.mappers;

import edu.unimagdalena.api.model.dto_save.ShipmentDetailsToSaveDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import edu.unimagdalena.api.model.entities.ShipmentDetails;
import edu.unimagdalena.api.model.dto.ShipmentDetailsDTO;

@Mapper
public interface ShipmentDetailsMapper {

    ShipmentDetailsMapper INSTANCE = Mappers.getMapper(ShipmentDetailsMapper.class);

    //@Mapping(source = "orderId", target = "order.id")
    ShipmentDetails  shipmentDetailsDtoToShipmentDetails(ShipmentDetailsDTO shipmentDetailsDTO);

    //@Mapping(source = "order.id", target = "orderId")
    ShipmentDetailsDTO shipmentDetailsToShipmentDetailsDto(ShipmentDetails shipmentDetails);

    @Mapping(target = "id", ignore = true)
    ShipmentDetails shipmentDetailsToSaveDtoToShipmentDetails(ShipmentDetailsToSaveDto shipmentDetailsToSaveDto);

    ShipmentDetailsToSaveDto shipmentDetailsToShipmentDetailsToSaveDto(ShipmentDetails shipmentDetails);

}