package com.example.payu.controller;

import com.example.payu.util.HashUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@Controller
public class PaymentController {

    private static final String MERCHANT_KEY = "yourMerchantKey";
    private static final String SALT = "yourSaltKey";

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

        String hashString = MERCHANT_KEY + "|" + txnid + "|" + amount + "|" + productinfo + "|" +
                firstname + "|" + email + "|||||||||||" + SALT;
        String hash = HashUtil.generateHash(hashString);

        model.addAttribute("key", MERCHANT_KEY);
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