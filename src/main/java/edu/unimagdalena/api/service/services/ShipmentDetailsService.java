package edu.unimagdalena.api.service.services;

import java.util.List;

import edu.unimagdalena.api.model.dto.ShipmentDetailsDTO;
import edu.unimagdalena.api.model.dto_save.ShipmentDetailsToSaveDto;
import edu.unimagdalena.api.model.enums.OrderStatus;

public interface ShipmentDetailsService {

    // CRUD
    ShipmentDetailsDTO create(ShipmentDetailsToSaveDto shipmentDetailsToSaveDto);

    ShipmentDetailsDTO getShipmentDetailById(Long shipmentDetailId);

    ShipmentDetailsDTO update(ShipmentDetailsToSaveDto shipmentDetailsToSaveDto, Long shipmentDetailId);

    void delete(Long shipmentDetailId);

    // Others methods

    List<ShipmentDetailsDTO> getAllShipmentDetails();

    ShipmentDetailsDTO getByOrderId(Long orderId);

    List<ShipmentDetailsDTO> getByTransporter(String transporter);

    List<ShipmentDetailsDTO> getByOrderStatus(OrderStatus orderStatus);
}
