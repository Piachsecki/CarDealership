package org.example.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString(of = {"carToBuyId", "vin", "brand", "model"})
@EqualsAndHashCode(of = "carToBuyId")
@Table(name = "car_to_buy")
public class CarToBuyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_to_buy_id")
    private Integer carToBuyId;

    @Column(name = "vin", unique = true)
    private String vin;
    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;
    @Column(name = "year")
    private Short year;
    @Column(name = "color")
    private String color;
    @Column(name = "price")
    private BigDecimal price;


    @OneToOne(fetch = FetchType.LAZY, mappedBy = "carToBuy", cascade = CascadeType.MERGE)
    private InvoiceEntity invoiceEntity;
}
