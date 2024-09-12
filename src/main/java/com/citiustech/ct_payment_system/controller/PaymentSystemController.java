package com.citiustech.ct_payment_system.controller;

import com.citiustech.ct_payment_system.service.PaymentSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/payments")
public class PaymentSystemController {

    @Autowired
    private PaymentSystemService paymentSystemService;

    // API to process payments for approved claims
    @PostMapping("/process-payment")
    public String processPayment(@RequestParam String claimId, Model model) {
        // Call service to process the payment
        String view = paymentSystemService.processPayment(claimId, model);
        return view;  // Return the appropriate view (either success, error, or not approved)
    }

    // API to view payment disbursement details
    @GetMapping("/disbursement-view")
    public String viewPaymentDisbursement(@RequestParam String claimId, Model model) {
        // Call service to get the payment details and return the view
        paymentSystemService.viewPaymentDisbursementDetails(claimId, model);
        return "payment-disbursement-view";
    }
}
