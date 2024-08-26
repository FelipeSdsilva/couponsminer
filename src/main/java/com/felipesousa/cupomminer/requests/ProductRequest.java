package com.felipesousa.cupomminer.requests;

import com.felipesousa.cupomminer.dto.ProductResponseDTO;
import com.felipesousa.cupomminer.exceptions.ResourceNotFoundException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ProductRequest {

    public static ProductResponseDTO getProduct(String ean) throws Exception {
        String apiUrl = "http://localhost:8081/api/products/" + ean;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonObject = new JSONObject(response.body());

        if (response.statusCode() == 200) {

            ProductResponseDTO product = new ProductResponseDTO();
            product.setEan(jsonObject.getLong("ean"));
            product.setProductName(jsonObject.getString("ProductName"));
            product.setMinPrice(BigDecimal.valueOf(jsonObject.getDouble("minPrice")));
            product.setMaxPrice(BigDecimal.valueOf(jsonObject.getDouble("maxPrice")));

            return product;
        } else {
            throw new ResourceNotFoundException("Ean not found");
        }
    }
}