package com.example.payu.controller;

import com.example.payu.util.HashUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@Controller
public class PaymentController {

    @Value("${payu.merchant.key}")
    private String merchantKey;

    @Value("${payu.salt.key}")
    private String salt;

    @GetMapping("/")
    public String home() {
        return "checkout";
    }

    @PostMapping("/pay")
    public String initiatePayment(@RequestParam String firstname,
                                  @RequestParam String email,
                                  @RequestParam String amount,
                                  Model model) throws Exception {

        String txnid = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
        String productinfo = "Test Product";

        String hashString = merchantKey + "|" + txnid + "|" + amount + "|" + productinfo + "|" +
                firstname + "|" + email + "|||||||||||" + salt;
        String hash = HashUtil.generateHash(hashString);

        model.addAttribute("key", merchantKey);
        model.addAttribute("txnid", txnid);
        model.addAttribute("amount", amount);
        model.addAttribute("productinfo", productinfo);
        model.addAttribute("firstname", firstname);
        model.addAttribute("email", email);
        model.addAttribute("phone", "9999999999");
        model.addAttribute("surl", "http://localhost:8080/success");
        model.addAttribute("furl", "http://localhost:8080/failure");
        model.addAttribute("hash", hash);
        model.addAttribute("action", "https://sandboxsecure.payu.in/_payment");

        return "payuForm";
    }

    @PostMapping("/success")
    public String success(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("response", params);
        return "response";
    }

    @PostMapping("/failure")
    public String failure(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("response", params);
        return "response";
    }
}