package edu.unimagdalena.api.controller;

import edu.unimagdalena.api.model.dto.ShipmentDetailsDTO;
import edu.unimagdalena.api.exceptions.NotAbleToDeleteException;
import edu.unimagdalena.api.exceptions.ObjectNotFoundException;
import edu.unimagdalena.api.model.dto_save.ShipmentDetailsToSaveDto;
import edu.unimagdalena.api.service.services.ShipmentDetailsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shipping")
public class ShipmentDetailsController {
    private ShipmentDetailsService shipmentDetailsService;

    @GetMapping("")
    public ResponseEntity<List<ShipmentDetailsDTO>> getAllShipmentDetails() {
        List<ShipmentDetailsDTO> shipmentDetails = shipmentDetailsService.getAllShipmentDetails();
        return ResponseEntity.ok(shipmentDetails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShipmentDetailsDTO> getShipmentDetailsById(@PathVariable("id") Long id) {
        ShipmentDetailsDTO shipmentDetails = shipmentDetailsService.getShipmentDetailById(id);
        return ResponseEntity.ok(shipmentDetails);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<ShipmentDetailsDTO> getShipmentDetailsByOrderId(@PathVariable("orderId") Long orderId) {
        try {
            ShipmentDetailsDTO shipmentDetails = shipmentDetailsService.getByOrderId(orderId);
            return ResponseEntity.ok(shipmentDetails);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/carrier?name={name}")
    public ResponseEntity<List<ShipmentDetailsDTO>> getShipmentDetailsByCarrier(@PathVariable("name") String name) {
        try {
            List<ShipmentDetailsDTO> shipmentDetails = shipmentDetailsService.getByTransporter(name);
            return ResponseEntity.ok(shipmentDetails);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<ShipmentDetailsDTO> create(ShipmentDetailsToSaveDto shipmentDetailsToSaveDto) {
        ShipmentDetailsDTO shipmentDetailsDTO = shipmentDetailsService.create(shipmentDetailsToSaveDto);
        return ResponseEntity.status(201).body(shipmentDetailsDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShipmentDetailsDTO> update(@PathVariable("id") Long id, @Valid @RequestBody ShipmentDetailsToSaveDto shipmentDetailsToSaveDto) {
        try {
            ShipmentDetailsDTO shipmentDetailsDTO = shipmentDetailsService.update(shipmentDetailsToSaveDto, id);
            return ResponseEntity.ok(shipmentDetailsDTO);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ShipmentDetailsDTO> delete(@PathVariable("id") Long id) {
        try {
            shipmentDetailsService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NotAbleToDeleteException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
