package com.example.monobank;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import okhttp3.RequestBody;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class PaymentController {
    private static final String API_BASE_URL = "https://api.monobank.ua";

    @PostMapping("create")
    public static ResponseEntity<?> createInvoice(@org.springframework.web.bind.annotation.RequestBody PaymentRequest request)
            throws JsonProcessingException {

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(request);
        RequestBody body = RequestBody.create(mediaType,json);

        Request requestToApi = new Request.Builder()
                .url(API_BASE_URL + "/api/merchant/invoice/create")
                .post(body)
                .addHeader("X-Token", "uVT6_cBKDbrjpakBPDD4rbM5uzudfD0KnzfmPItnePQ8")
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(requestToApi).execute();
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JSONObject jsonObject = new JSONObject(responseBody);

                CreateInvoiceResponse paymentResponse = objectMapper.readValue(jsonObject.toString(),
                        new TypeReference<CreateInvoiceResponse>() {});

                System.out.println(responseBody);

                return ResponseEntity.ok(paymentResponse);

            } else {
                System.out.println("Помилка при створенні платежу");
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Помилка при створенні платежу");
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("status/{invoiceId}")
    private static ResponseEntity<?> checkInvoiceStatus(@PathVariable(name ="invoiceId") String invoiceId) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(API_BASE_URL + "/api/merchant/invoice/status?invoiceId=" + invoiceId)
                .get()
                .addHeader("X-Token", "uVT6_cBKDbrjpakBPDD4rbM5uzudfD0KnzfmPItnePQ8")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JSONObject jsonObject = new JSONObject(responseBody);
                ObjectMapper objectMapper = new ObjectMapper();
                StatusResponse statusResponse = objectMapper.readValue(jsonObject.toString(), StatusResponse.class);

                // Опрацювання відповіді API на перевірку статусу платежу
                System.out.println(responseBody);
                return ResponseEntity.ok(statusResponse);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }
}
