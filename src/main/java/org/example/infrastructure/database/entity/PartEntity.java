package org.example.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"serialNumber", "partId", "price"})
@EqualsAndHashCode(of = "partId")
@Table(name = "part")
public class PartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "part_id")
    private Integer partId;
    @Column(name = "serial_number", unique = true)
    private String serialNumber;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private BigDecimal price;



    @OneToMany(fetch = FetchType.LAZY, mappedBy = "part")
    private Set<ServicePartEntity> serviceParts;

}
