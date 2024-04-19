package edu.unimagdalena.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.unimagdalena.api.model.entities.ShipmentDetails;
import edu.unimagdalena.api.model.enums.OrderStatus;

@Repository
public interface ShipmentDetailsRepository extends JpaRepository<ShipmentDetails, Long> {

    @Query("SELECT s FROM ShipmentDetails s WHERE s.order.id = ?1")
    ShipmentDetails findByOrderId(Long orderId);

    @Query("SELECT s FROM ShipmentDetails s WHERE s.transporter = ?1")
    List<ShipmentDetails> findByTransporter(String transporter);

    @Query("SELECT s FROM ShipmentDetails s WHERE s.order.status = ?1")
    List<ShipmentDetails> findByOrderStatus(OrderStatus orderStatus);
}