package com.citiustech.ct_payment_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "claim_id")
    private String claimId;

    @Column(name = "payment_amount")
    private double paymentAmount;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "provider_acc_number")
    private String providerAccNumber;

}
