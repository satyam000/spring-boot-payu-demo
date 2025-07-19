# Spring Boot PayU Money Demo (Test Mode)

This Spring Boot project demonstrates integration with PayU Money using server-side SHA-512 hash generation and auto-submitting HTML forms.

## Features
- Form-based integration with sandbox PayU server
- Secure hash generation
- Response handling
- Sample Thymeleaf frontend
- Environment variable-based merchant key and salt

## Setup

Set your environment variables before running:

```bash
export PAYU_MERCHANT_KEY=yourMerchantKey
export PAYU_SALT_KEY=yourSaltKey
```

Then run:

```bash
mvn spring-boot:run
```

Visit: [http://localhost:8080](http://localhost:8080)