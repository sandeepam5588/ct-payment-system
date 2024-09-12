package com.citiustech.ct_payment_system.repository;

import com.citiustech.ct_payment_system.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {

    Payment findByClaimId(String claimId);
}
