package com.example.demo.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.PaymentServicd;

@RestController
public class PaymentController {
	private final PaymentServicd paymentService;

    public PaymentController(PaymentServicd paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/pay")
    public String pay() {
        return paymentService.processPayment();
    }
	

}
