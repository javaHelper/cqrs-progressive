# Event Souring and CQRS with Axon Framework

# Create Account 

```sh
curl --location --request POST 'http://localhost:8080/bank-accounts' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=F3978A05C8315B430A6159B61219FC09' \
--data-raw '{
    "startingBalance": 500,
    "currency": "USD"
}'
```

Response: `d5d48e48-ad3f-4640-8cbb-4e1328a7b918`

-----

# Credit Account

```sh
curl --location --request PUT 'http://localhost:8080/bank-accounts/credits/d5d48e48-ad3f-4640-8cbb-4e1328a7b918' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=F3978A05C8315B430A6159B61219FC09' \
--data-raw '{
    "creditAmount": "250",
    "currency": "USD"
}'
```

----

# Debit Account 

- Make multiple Request (one by one )

```sh
curl --location --request PUT 'http://localhost:8080/bank-accounts/debits/d5d48e48-ad3f-4640-8cbb-4e1328a7b918' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=F3978A05C8315B430A6159B61219FC09' \
--data-raw '{
    "debitAmount": "999",
    "currency": "USD"
}'
```
and then check

GET -> `http://localhost:8080/bank-accounts/d5d48e48-ad3f-4640-8cbb-4e1328a7b918`

GET -> `http://localhost:8080/bank-accounts/d5d48e48-ad3f-4640-8cbb-4e1328a7b918/events`

Response:

```json
[
    {
        "id": "d5d48e48-ad3f-4640-8cbb-4e1328a7b918",
        "accountBalance": 500.0,
        "currency": "USD"
    },
    {
        "id": "d5d48e48-ad3f-4640-8cbb-4e1328a7b918",
        "status": "ACTIVATED"
    },
    {
        "id": "d5d48e48-ad3f-4640-8cbb-4e1328a7b918",
        "creditAmount": 250.0,
        "currency": "USD"
    },
    {
        "id": "d5d48e48-ad3f-4640-8cbb-4e1328a7b918",
        "debitAmount": 199.0,
        "currency": "USD"
    },
    {
        "id": "d5d48e48-ad3f-4640-8cbb-4e1328a7b918",
        "debitAmount": 999.0,
        "currency": "USD"
    },
    {
        "id": "d5d48e48-ad3f-4640-8cbb-4e1328a7b918",
        "status": "HOLD"
    }
]
```
