# Event Sourcing With CQRS

Note: Make sure AXON server is up and running. I am using Docker

# Create Bank Account

```sh
curl --location --request POST 'http://localhost:8080/bank-accounts' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=F3978A05C8315B430A6159B61219FC09' \
--data-raw '{
    "startingBalance": 100,
    "currency": "USD"
}'
```

Response:

```
10a34dae-0bed-4e1d-833a-9e20af8a504f
```

---------

# Credit Amount

```sh
curl --location --request PUT 'http://localhost:8080/bank-accounts/credits/10a34dae-0bed-4e1d-833a-9e20af8a504f' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=F3978A05C8315B430A6159B61219FC09' \
--data-raw '{
    "creditAmount": "200",
    "currency": "USD"
}'
```
-----------

# Debit Amount

```sh
curl --location --request PUT 'http://localhost:8080/bank-accounts/debits/10a34dae-0bed-4e1d-833a-9e20af8a504f' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=F3978A05C8315B430A6159B61219FC09' \
--data-raw '{
    "debitAmount": "199",
    "currency": "USD"
}'
```

--------
# Get All Events

```
http://localhost:8080/bank-accounts/10a34dae-0bed-4e1d-833a-9e20af8a504f/events
```

Response:

```
[
    {
        "id": "10a34dae-0bed-4e1d-833a-9e20af8a504f",
        "accountBalance": 100.0,
        "currency": "USD"
    },
    {
        "id": "10a34dae-0bed-4e1d-833a-9e20af8a504f",
        "status": "ACTIVATED"
    },
    {
        "id": "10a34dae-0bed-4e1d-833a-9e20af8a504f",
        "creditAmount": 200.0,
        "currency": "USD"
    },
    {
        "id": "10a34dae-0bed-4e1d-833a-9e20af8a504f",
        "debitAmount": 199.0,
        "currency": "USD"
    }
]
```

# Check commands in Axon Server

<img width="830" alt="Screenshot 2022-07-25 at 11 45 14 AM" src="https://user-images.githubusercontent.com/54174687/180710495-925e91f6-3286-4da8-a428-99f0b3e206f3.png">


<img width="1443" alt="Screenshot 2022-07-25 at 11 46 09 AM" src="https://user-images.githubusercontent.com/54174687/180710564-cb3db7e3-868f-461d-8c60-e0a3b9923d20.png">
