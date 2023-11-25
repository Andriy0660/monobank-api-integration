package com.example.monobank;

import lombok.Data;

@Data
public class PaymentRequest {
    private Integer amount;
    private Integer ccy;
    private MerchantPaymInfo merchantPaymInfo;
    private String redirectUrl;

    public PaymentRequest() {
    }

    public PaymentRequest(Integer amount, Integer ccy, MerchantPaymInfo merchantPaymInfo, String redirectUrl) {
        this.amount = amount;
        this.ccy = ccy;
        this.merchantPaymInfo = merchantPaymInfo;
        this.redirectUrl = redirectUrl;
    }

    @Data
    public static class MerchantPaymInfo{
        private String reference;
        private String destination;
        private BasketOrder[] basketOrder;

        public MerchantPaymInfo() {
        }

        public MerchantPaymInfo(String reference, String destination, BasketOrder[] basketOrder) {
            this.reference = reference;
            this.destination = destination;
            this.basketOrder = basketOrder;
        }
    }
    @Data
    public static class BasketOrder{
        private String name;
        private Integer qty;
        private Integer sum;
        private String icon;
        private String unit;

        public BasketOrder() {
        }

        public BasketOrder(String name, Integer qty, Integer sum, String icon, String unit) {
            this.name = name;
            this.qty = qty;
            this.sum = sum;
            this.icon = icon;
            this.unit = unit;
        }
    }
}


