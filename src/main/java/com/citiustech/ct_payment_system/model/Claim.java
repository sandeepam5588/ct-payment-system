package com.citiustech.ct_payment_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Claim {
    private String claimId;
    private String providerId;
    private String memberId;
    private String serviceList;
    private int fraudFlag;
    private String claimStatus;
    private String payerCostShare;
    private String memberCostShare;
    private String eobDetails;
    private String denialReasons;
    private double totalAmount;
}
