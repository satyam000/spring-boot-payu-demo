# Spring Boot PayU Money Demo (Test Mode)

This Spring Boot project demonstrates integration with PayU Money using server-side SHA-512 hash generation and auto-submitting HTML forms.

## Features
- Form-based integration with sandbox PayU server
- Secure hash generation
- Response handling
- Sample Thymeleaf frontend

## Usage
1. Replace `yourMerchantKey` and `yourSaltKey` in `PaymentController.java`
2. Run the app:
   ```
   mvn spring-boot:run
   ```
3. Open: `http://localhost:8080`