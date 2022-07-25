# Saga Pattern Implementation with Axon and Spring Boot!

# Create Order

```sh
curl --location --request POST 'http://localhost:8080/api/orders' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=F3978A05C8315B430A6159B61219FC09' \
--data-raw '{
    "itemType": "HEADPHONE",
    "price": 330,
    "currency": "USD"
}'
```

Response: `6ee3b4c4-ccf0-4dad-bbf6-8cf9acf07359`

-----

<img width="598" alt="Screenshot 2022-07-25 at 6 23 38 PM" src="https://user-images.githubusercontent.com/54174687/180782280-1507d029-5c39-4435-85fa-824537419391.png">
