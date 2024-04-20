package edu.unimagdalena.api.servicesTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import edu.unimagdalena.api.model.dto_save.ShipmentDetailsToSaveDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import edu.unimagdalena.api.model.entities.Order;
import edu.unimagdalena.api.model.entities.ShipmentDetails;
import edu.unimagdalena.api.model.dto.ShipmentDetailsDTO;
import edu.unimagdalena.api.model.enums.OrderStatus;
import edu.unimagdalena.api.model.mappers.ShipmentDetailsMapper;
import edu.unimagdalena.api.repository.OrderRepository;
import edu.unimagdalena.api.repository.ShipmentDetailsRepository;
import edu.unimagdalena.api.service.implementations.ShipmentDetailsImpl;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ShipmentDetailsImplTest {

    @Mock
    ShipmentDetailsRepository shipmentDetailsRepository;

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    ShipmentDetailsImpl shipmentDetailsService;

    Order order1 = Order.builder()
                .id(1L)
                .orderDate(LocalDateTime.now())
                .status(OrderStatus.SENT)
                .build();
    
    ShipmentDetails shipmentDetails1 = ShipmentDetails.builder()
                    .id(2L)
                    .order(order1)
                    .shipmentAddress("1234")
                    .transporter("coordinadora")
                    .guideNumber((long) 1234)
                    .build();

    ShipmentDetails shipmentDetails2 = ShipmentDetails.builder()
                    .id(3L)
                    .order(order1)
                    .shipmentAddress("12345")
                    .transporter("servi entrega")
                    .guideNumber((long) 12345)
                    .build();

    ShipmentDetailsToSaveDto shipmentDetailsToSaveDto = ShipmentDetailsMapper.INSTANCE.shipmentDetailsToShipmentDetailsToSaveDto(shipmentDetails1);
    ShipmentDetailsToSaveDto shipmentDetailsToSaveDto1 = ShipmentDetailsMapper.INSTANCE.shipmentDetailsToShipmentDetailsToSaveDto(shipmentDetails2);

    @BeforeEach
    void setUp(){
        when(shipmentDetailsRepository.findAll()).thenReturn(List.of(shipmentDetails1, shipmentDetails2));
        when(shipmentDetailsRepository.findById(anyLong())).thenReturn(Optional.ofNullable(shipmentDetails1));
        when(shipmentDetailsRepository.save(any())).thenReturn(shipmentDetails1);
        when(orderRepository.findById(anyLong())).thenReturn(Optional.ofNullable(order1));
    }



    @Test
    void testCreate() {
        //given
        ShipmentDetailsDTO saved = shipmentDetailsService.create(shipmentDetailsToSaveDto);
        //then
        assertThat(saved).isNotNull();
        assertEquals(2L, saved.id());
    }

    @Test
    void testDelete() {
        //when
        ShipmentDetailsDTO saved = shipmentDetailsService.create(shipmentDetailsToSaveDto);
        when(shipmentDetailsRepository.count()).thenReturn(2L);
        assertEquals(2L, shipmentDetailsRepository.count());
        //then
        shipmentDetailsService.delete(saved.id());
        when(shipmentDetailsRepository.count()).thenReturn(0L);
        assertEquals(0L, shipmentDetailsRepository.count());
    }

    @Test
    void testGetAllShipmentDetails() {
        //given
        shipmentDetailsService.create(shipmentDetailsToSaveDto);
        //when
        List<ShipmentDetailsDTO> res = shipmentDetailsService.getAllShipmentDetails();
        //then
        assertEquals(2, res.size());
    }

    @Test
    void testGetByOrderId() {
        //given
        ShipmentDetailsDTO saved = shipmentDetailsService.create(shipmentDetailsToSaveDto);
        when(shipmentDetailsRepository.findByOrderId(anyLong())).thenReturn(shipmentDetails1);
        //when
        ShipmentDetailsDTO res = shipmentDetailsService.getByOrderId(1L);
        //then
        assertEquals(saved.id(), res.id());
    }

    @Test
    void testGetByOrderStatus() {
        //given
        ShipmentDetailsDTO saved = shipmentDetailsService.create(shipmentDetailsToSaveDto);
        when(shipmentDetailsRepository.findByOrderStatus(any(OrderStatus.class))).thenReturn(List.of(shipmentDetails1));
        //when
        List<ShipmentDetailsDTO> res = shipmentDetailsService.getByOrderStatus(OrderStatus.SENT);
        //then
        assertEquals(saved.id(), res.get(0).id());
    }

    @Test
    void testGetByTransporter() {
        //given
        ShipmentDetailsDTO saved = shipmentDetailsService.create(shipmentDetailsToSaveDto);
        when(shipmentDetailsRepository.findByTransporter(anyString())).thenReturn(List.of(shipmentDetails1));
        //when
        List<ShipmentDetailsDTO> res = shipmentDetailsService.getByTransporter(saved.transporter());
        //then
        assertEquals(saved.id(), res.get(0).id());
    }

    @Test
    void testGetShipmentDetailById() {
        //given
        ShipmentDetailsDTO saved = shipmentDetailsService.create(shipmentDetailsToSaveDto);
        when(shipmentDetailsRepository.findById(anyLong())).thenReturn(Optional.ofNullable(shipmentDetails1));
        //when
        ShipmentDetailsDTO res = shipmentDetailsService.getShipmentDetailById(saved.id());
        //then
        assertEquals(saved.id(), res.id());
    }

    @Test
    void testUpdate() {
        //given
        shipmentDetailsService.create(shipmentDetailsToSaveDto);
        //when
        ShipmentDetailsDTO shipmentDetailsUpdate = shipmentDetailsService.update(shipmentDetailsToSaveDto1, 2L);
        //then
        assertEquals(12345L, shipmentDetailsUpdate.guideNumber());
    }
}
