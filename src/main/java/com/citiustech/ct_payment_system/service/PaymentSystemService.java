package com.citiustech.ct_payment_system.service;

import com.citiustech.ct_payment_system.model.Claim;
import com.citiustech.ct_payment_system.model.Payment;
import com.citiustech.ct_payment_system.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class PaymentSystemService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String CLAIMS_SERVICE_URL = "http://localhost:8081/claims"; // URL of Claims Processing System

    // Process payment for an approved claim
    public String processPayment(String claimId, Model model) {
        // Call the Claims Processing API to fetch claim details
        Claim claim = fetchClaimFromClaimsService(claimId);

        if (claim == null) {
            model.addAttribute("message", "Claim not found");
            return "error-view"; // Return error view if claim not found
        }

        // Check if the claim is approved for payment
        String claimStatus = claim.getClaimStatus();
        if ("APPROVED".equals(claimStatus)) {
            // Logic for processing payment
            Payment payment = new Payment();
            payment.setClaimId(claimId);
            payment.setPaymentAmount(claim.getTotalAmount());
            payment.setPaymentMethod("EFT");  // Default payment method
            payment.setPaymentDate(LocalDateTime.now());

            // Save payment in the database
            paymentRepository.save(payment);

            model.addAttribute("payment", payment);
            return "payment-success-view";  // Return payment success view
        } else if ("DENIED".equals(claimStatus) || "REJECTED".equals(claimStatus)) {
            model.addAttribute("message", "Claim denied/rejected, payment cannot be processed.");
            return "claim-denied-view";  // Return view indicating claim denial
        } else {
            model.addAttribute("message", "Claim is not yet approved for payment processing.");
            return "claim-not-approved-view";  // Return view if the claim is not approved
        }
    }

    // Fetch claim from the Claims Processing System via API
    private Claim fetchClaimFromClaimsService(String claimId) {
        try {
            ResponseEntity<Claim> response = restTemplate.getForEntity(
                    CLAIMS_SERVICE_URL + "/get-claim?claimId=" + claimId, Claim.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;  // Return null if an error occurs
        }
    }

    // View payment disbursement details
    public void viewPaymentDisbursementDetails(String claimId, Model model) {
        Payment payment = paymentRepository.findByClaimId(claimId);
        model.addAttribute("payment", payment);
    }
}
