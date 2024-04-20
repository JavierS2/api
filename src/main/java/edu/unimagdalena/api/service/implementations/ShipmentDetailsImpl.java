package edu.unimagdalena.api.service.implementations;

import java.util.List;
import java.util.Objects;

import edu.unimagdalena.api.model.dto_save.ShipmentDetailsToSaveDto;
import org.springframework.stereotype.Service;

import edu.unimagdalena.api.model.entities.ShipmentDetails;
import edu.unimagdalena.api.model.dto.ShipmentDetailsDTO;
import edu.unimagdalena.api.model.enums.OrderStatus;
import edu.unimagdalena.api.exceptions.NotAbleToDeleteException;
import edu.unimagdalena.api.exceptions.ObjectNotFoundException;
import edu.unimagdalena.api.model.mappers.ShipmentDetailsMapper;
import edu.unimagdalena.api.repository.ShipmentDetailsRepository;
import edu.unimagdalena.api.service.services.ShipmentDetailsService;

@Service
public class ShipmentDetailsImpl implements ShipmentDetailsService {

    private final ShipmentDetailsRepository shipmentDetailsRepository;

    public ShipmentDetailsImpl(ShipmentDetailsRepository shipmentDetailsRepository) {
        this.shipmentDetailsRepository = shipmentDetailsRepository;
    }

    @Override
    public ShipmentDetailsDTO create(ShipmentDetailsToSaveDto shipmentDetailsToSaveDto) {
        ShipmentDetails shipmentDetailsSaved = shipmentDetailsRepository
                .save(ShipmentDetailsMapper.INSTANCE.shipmentDetailsToSaveDtoToShipmentDetails(shipmentDetailsToSaveDto));
        return ShipmentDetailsMapper.INSTANCE.shipmentDetailsToShipmentDetailsDto(shipmentDetailsSaved);
    }

    @Override
    public ShipmentDetailsDTO getShipmentDetailById(Long shipmentDetailId) {
        ShipmentDetails shipmentDetails = shipmentDetailsRepository.findById(shipmentDetailId)
                .orElseThrow(() -> new ObjectNotFoundException("ShipmentDetails not found"));
        return ShipmentDetailsMapper.INSTANCE.shipmentDetailsToShipmentDetailsDto(shipmentDetails);
    }

    @Override
    public ShipmentDetailsDTO update(ShipmentDetailsToSaveDto shipmentDetailsToSaveDto, Long shipmentDetailId) {
        ShipmentDetails shipmentDetailsInDb = shipmentDetailsRepository.findById(shipmentDetailId)
                .orElseThrow(() -> new ObjectNotFoundException("ShipmentDetails not found"));
        shipmentDetailsInDb.setTransporter(shipmentDetailsToSaveDto.transporter());
        shipmentDetailsInDb.setGuideNumber(shipmentDetailsToSaveDto.guideNumber());
        shipmentDetailsInDb.setShipmentAddress(shipmentDetailsToSaveDto.shipmentAddress());
        return ShipmentDetailsMapper.INSTANCE.shipmentDetailsToShipmentDetailsDto(shipmentDetailsRepository
                .save(shipmentDetailsInDb));
    }

    @Override
    public void delete(Long shipmentDetailId) {
        ShipmentDetails shipmentDetails = shipmentDetailsRepository.findById(shipmentDetailId)
                .orElseThrow(() -> new NotAbleToDeleteException("ShipmentDetails not found, not able to delete"));
        shipmentDetailsRepository.delete(shipmentDetails);
    }

    @Override
    public List<ShipmentDetailsDTO> getAllShipmentDetails() {
        List<ShipmentDetails> shipmentDetails = shipmentDetailsRepository.findAll();
        return shipmentDetails.stream()
                .map(ShipmentDetailsMapper.INSTANCE::shipmentDetailsToShipmentDetailsDto)
                .toList();
    }

    @Override
    public ShipmentDetailsDTO getByOrderId(Long orderId) {
        ShipmentDetails shipmentDetails = shipmentDetailsRepository.findByOrderId(orderId);
        if (Objects.isNull(shipmentDetails)) {
            throw new ObjectNotFoundException("ShipmentDetails not found");
        }
        return ShipmentDetailsMapper.INSTANCE.shipmentDetailsToShipmentDetailsDto(shipmentDetails);
    }

    @Override
    public List<ShipmentDetailsDTO> getByTransporter(String transporter) {
        List<ShipmentDetails> shipmentDetails = shipmentDetailsRepository.findByTransporter(transporter);
        return shipmentDetails.stream().map(ShipmentDetailsMapper.INSTANCE::shipmentDetailsToShipmentDetailsDto).toList();
    }

    @Override
    public List<ShipmentDetailsDTO> getByOrderStatus(OrderStatus orderStatus) {
        List<ShipmentDetails> shipmentDetails = shipmentDetailsRepository.findByOrderStatus(orderStatus);
        return shipmentDetails.stream().map(ShipmentDetailsMapper.INSTANCE::shipmentDetailsToShipmentDetailsDto).toList();
    }
}
