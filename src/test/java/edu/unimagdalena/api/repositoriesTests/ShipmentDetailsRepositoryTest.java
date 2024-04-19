package edu.unimagdalena.api.repositoriesTests;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.unimagdalena.api.AbstractIntegrationDBTest;
import edu.unimagdalena.api.model.entities.Order;
import edu.unimagdalena.api.model.entities.ShipmentDetails;
import edu.unimagdalena.api.model.enums.OrderStatus;
import edu.unimagdalena.api.repository.OrderRepository;
import edu.unimagdalena.api.repository.ShipmentDetailsRepository;

public class ShipmentDetailsRepositoryTest extends AbstractIntegrationDBTest{

    ShipmentDetailsRepository shipmentDetailsRepository;
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    public ShipmentDetailsRepositoryTest(ShipmentDetailsRepository shipmentDetailsRepository){
        this.shipmentDetailsRepository = shipmentDetailsRepository;
    }

    Order order1 = Order.builder()
                .customer(null)
                .orderDate(LocalDateTime.now())
                .status(OrderStatus.SENT)
                .items(null)
                .payment(null)
                .shipmentDetalis(null)
                .build();
    
    ShipmentDetails shipmentDetails1 = ShipmentDetails.builder()
                    .order(null)
                    .shipmentAddress("1234")
                    .transporter("coordinadora")
                    .guideNumber((long) 1234)
                    .build();

    ShipmentDetails shipmentDetails2 = ShipmentDetails.builder()
                    .order(null)
                    .shipmentAddress("12345")
                    .transporter("servi entrega")
                    .guideNumber((long) 12345)
                    .build();   

    @BeforeEach
    void setUp(){
        shipmentDetailsRepository.deleteAll();
    }

    @Test
    @DisplayName("test save")
    void givenAnShipmentDetails_WhenSave_ThenShipmentDetailsWithId(){
        //given
        ShipmentDetails shipmentDetailsSaved = shipmentDetailsRepository.save(shipmentDetails1);
        //then
        assertThat(shipmentDetailsSaved.getId()).isNotNull();
    }

    @Test
    @DisplayName("test read")
    void givenShipmentDetails_ThenGetAll(){
        //given
        shipmentDetailsRepository.save(shipmentDetails1);
        shipmentDetailsRepository.save(shipmentDetails2);
        List<ShipmentDetails> shipmentDetails = shipmentDetailsRepository.findAll();
        //then
        assertThat(shipmentDetails.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("test delete")
    void givenIdCustomer_thenDeleteIt(){
        //given
        shipmentDetailsRepository.save(shipmentDetails1);
        Long id = shipmentDetails1.getId();
        //when
        shipmentDetailsRepository.deleteById(id);
        //then
        assertThat(shipmentDetailsRepository.findById(id)).isEmpty();
    }

    @Test
    @DisplayName("test update")
    void givenAnCustomer_ThenUpdateThis(){
        //given
        Long id = shipmentDetailsRepository.save(shipmentDetails2).getId();
        String newTransporter = "servi entrega";
        //when
        ShipmentDetails shipmentDetails = shipmentDetailsRepository.findById(id).orElseThrow(null);
        if(shipmentDetails!= null){
            shipmentDetails.setTransporter(newTransporter);
            ShipmentDetails shipmentDetailsUpdate = shipmentDetailsRepository.save(shipmentDetails);
            //then
            assertThat(shipmentDetailsUpdate.getTransporter()).isEqualTo(newTransporter);
        }
    }

    @Test
    @DisplayName("test findByOrderId")
    void givenCustomer_ThenFindByOrderId(){
        //given
        Order saved = orderRepository.save(order1);
        Long id = saved.getId();
        shipmentDetails1.setOrder(saved);
        shipmentDetailsRepository.save(shipmentDetails1);
        shipmentDetailsRepository.save(shipmentDetails2);
        ShipmentDetails findShipmentDetails = shipmentDetailsRepository.findByOrderId(id);
        //then
        assertThat(findShipmentDetails.getOrder().getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("test findByTransporter")
    void givenCustomer_ThenFindByTransporter(){
        //given
        String transporter = shipmentDetails1.getTransporter();
        shipmentDetailsRepository.save(shipmentDetails1);
        List<ShipmentDetails> findShipmentDetails = shipmentDetailsRepository.findByTransporter(transporter);
        //then
        assertThat(findShipmentDetails.get(0).getTransporter()).isEqualTo(transporter);
    }

    @Test
    @DisplayName("test findByOrderStatus")
    void givenCustomer_ThenFindByOrderStatus(){
        //given
        OrderStatus orderStatus = order1.getStatus();
        orderRepository.save(order1);
        shipmentDetails1.setOrder(order1);
        shipmentDetailsRepository.save(shipmentDetails1);
        List<ShipmentDetails> findShipmentDetails = shipmentDetailsRepository.findByOrderStatus(orderStatus);
        //then
        assertThat(findShipmentDetails.size()).isEqualTo(1);
    }
}
