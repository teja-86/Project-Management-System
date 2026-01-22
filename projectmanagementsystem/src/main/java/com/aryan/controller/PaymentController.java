package com.aryan.controller;

import com.aryan.model.entity.PlanType;
import com.aryan.model.entity.User;
import com.aryan.response.PaymentLinkResponse;
import com.aryan.service.UserService;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Value("${razorpay.api.key}")
    private String apiKey;

    @Value("${razorpay.api.secret}")
    private String apiSecret;

    @Autowired
    private UserService userService;

    @PostMapping("/{planType}")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(
            @PathVariable PlanType planType,
            @RequestHeader("Authorization") String jwt
            ) throws Exception {

        User user = userService.findUserByJwt(jwt);
        int amount = 1*100;
        if(planType.equals(PlanType.ANNUALLY)){
            amount = amount*12;
            amount = (int) (amount*0.7);
        }

            RazorpayClient razorpayClient = new RazorpayClient(apiKey, apiSecret);

            JSONObject paymentLinkResponse = new JSONObject();
            paymentLinkResponse.put("amount", amount);
            paymentLinkResponse.put("currency", "INR");

            JSONObject customer = new JSONObject();
            customer.put("first_name", user.getFullName());
            customer.put("email", user.getEmail());

            paymentLinkResponse.put("customer", customer);

            JSONObject notify = new JSONObject();
            notify.put("email", true);
            paymentLinkResponse.put("notify", notify);

            paymentLinkResponse.put("callback_url", "http://localhost:5173/upgrade_plan/success?planType=" + planType);

            PaymentLink paymentLink = razorpayClient.paymentLink.create(paymentLinkResponse);

            String paymentLinkId = paymentLink.get("id");
            String paymentLinkUrl = paymentLink.get("short_url");

            PaymentLinkResponse res = new PaymentLinkResponse();
            res.setPayment_link_url(paymentLinkUrl);
            res.setPayment_link_id(paymentLinkId);

            return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

}
