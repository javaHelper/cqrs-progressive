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
<img width="1511" alt="Screenshot 2022-07-25 at 2 16 14 PM" src="https://user-images.githubusercontent.com/54174687/180736509-5efb20fe-4db3-422b-821b-2172a0a0c9c7.png">


<img width="1444" alt="Screenshot 2022-07-25 at 2 16 48 PM" src="https://user-images.githubusercontent.com/54174687/180736728-41a1cfcf-e0ce-4611-a6c1-7736c68b8611.png">



