# Desafio Camunda 8

## Caminhos

### Sucesso Total

```sh
curl -X POST http://localhost:8080/place-order -H "Content-Type: application/json" -d '{"orderId": "99", "paymentMethod": "DEBIT_CARD", "total": "200", "customerEmail":"teste@mail.com", "productId": "12", "amount": "2"}'
```

```sh
curl -X POST http://localhost:8080/confirm-stock -H "Content-Type: application/json" -d '{"processId": "99", "paymentStatus": "APPROVED", "productId": "12", "amount":"4", "customerEmail": "teste@mail.com", "orderId": "99"}'
```

### Compensation: Payment Refused 

Odd total:

```sh
curl -X POST http://localhost:8080/place-order -H "Content-Type: application/json" -d '{"orderId": "99", "paymentMethod": "DEBIT_CARD", "total": "333", "customerEmail":"teste@mail.com", "productId": "12", "amount": "2"}'
```

### Compensation: Out of stock

Odd amount

```sh
curl -X POST http://localhost:8080/place-order -H "Content-Type: application/json" -d '{"orderId": "99", "paymentMethod": "DEBIT_CARD", "total": "200", "customerEmail":"teste@mail.com", "productId": "12", "amount": "3"}'
```

### Compensation Cancelled

Non numeric orderId

```sh
curl -X POST http://localhost:8080/place-order -H "Content-Type: application/json" -d '{"orderId": "i99", "paymentMethod": "DEBIT_CARD", "total": "200", "customerEmail":"teste@mail.com", "productId": "12", "amount": "2"}'
```

```sh
curl -X POST http://localhost:8080/confirm-stock -H "Content-Type: application/json" -d '{"processId": "i99", "paymentStatus": "APPROVED", "productId": "12", "amount":"4", "customerEmail": "teste@mail.com", "orderId": "99"}'
```