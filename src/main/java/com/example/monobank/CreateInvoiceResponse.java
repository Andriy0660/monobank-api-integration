package com.example.monobank;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateInvoiceResponse {
    @JsonProperty("invoiceId")
    String invoiceId;
    @JsonProperty("pageUrl")
    String pageUrl;

    public CreateInvoiceResponse() {
    }

    public CreateInvoiceResponse(String invoiceId, String pageUrl) {
        this.invoiceId = invoiceId;
        this.pageUrl = pageUrl;
    }
}
