package com.example.monobank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatusResponse {
    String status;
    String failureReason;
    String createdDate;

    public StatusResponse() {
    }

    public StatusResponse(String status, String failureReason, String createdDate) {
        this.status = status;
        this.failureReason = failureReason;
        this.createdDate = createdDate;
    }
}
