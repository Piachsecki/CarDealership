package org.example.infrastructure.database.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString(of = {"mechanicId", "name", "surname"})
@EqualsAndHashCode(of = "mechanicId")
@Entity
@Table(name = "mechanic")
public class MechanicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mechanic_id")
    private Integer mechanicId;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "pesel", unique = true)
    private String pesel;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mechanic", cascade = CascadeType.ALL)
    private Set<ServiceMechanicEntity> serviceMechanics;
}
