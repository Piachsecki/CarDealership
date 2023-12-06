package org.example.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString(of = {"carToServiceId", "vin", "brand"})
@EqualsAndHashCode(of = "carToServiceId")
@Table(name = "car_to_service")
public class CarToServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_to_service_id")
    private Integer carToServiceId;
    @Column(name = "vin", unique = true)
    private String vin    ;
    @Column(name = "brand")
    private String brand  ;
    @Column(name = "model")
    private String model  ;
    @Column(name = "year")
    private Short year;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "carToService")
    private Set<CarServiceRequestEntity> carServiceRequests;

}

