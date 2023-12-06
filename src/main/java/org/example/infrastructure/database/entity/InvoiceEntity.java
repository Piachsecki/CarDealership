package org.example.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString(of = {"invoiceId", "invoiceNumber", "dateTime"})
@EqualsAndHashCode(of = "invoiceId")
@Table(name = "invoice")
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private Integer invoiceId;
    @Column(name = "invoice_number", unique = true)
    private String invoiceNumber;
    @Column(name = "date_time")
    private OffsetDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salesman_id")
    private SalesmanEntity salesman;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "car_to_buy_id", unique = true)
    private CarToBuyEntity carToBuy;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
}
