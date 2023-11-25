# monobank-api-integration

## API Reference

### Create invoice


  `POST /create`

### Accepts

| Parameter | Type                 |
| :-------- | :------------------- |
| `request` | `PaymentRequest` |

### `PaymentRequest` {
    Integer amount;
    Integer ccy;
    MerchantPaymInfo merchantPaymInfo;
    String redirectUrl;
}

### `MerchantPaymInfo` {
    String reference;
    String destination;
    BasketOrder[] basketOrder;
}
### `BasketOrder` {
    String name;
    Integer qty;
    Integer sum;
    String icon;
    String unit;

}

### Returns
Returns an object of type `CreateInvoiceResponse`

### `CreateInvoiceResponse` {
    String invoiceId;
    String pageUrl;
}

`pageUrl` - link to the page to which the user needs to be redirected for payment

### Get status


  `GET status/{invoiceId}`

### Accepts

| Parameter | Type                 |
| :-------- | :------------------- |
| `invoiceId` | `String` |

### Returns
Returns an object of type `StatusResponse`

### `StatusResponse` {
    String status;
    String failureReason;
    String createdDate;
}


