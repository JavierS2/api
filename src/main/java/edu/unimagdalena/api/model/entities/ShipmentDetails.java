package edu.unimagdalena.api.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "shipment_details")
public class ShipmentDetails {

    @NotNull(message = "Id is mandatory")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Order is mandatory")
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @NotBlank(message = "Shipment address is mandatory")
    @Size(min = 5, message = "Shipment address must be at least 3 characters")
    @Column(nullable = false)
    private String shipmentAddress;

    @NotBlank(message = "Transporter is mandatory")
    @Column(nullable = true)
    private String transporter;

    @NotNull(message = "Guide number is mandatory")
    @Column(unique = true)
    private Long guideNumber;

}
