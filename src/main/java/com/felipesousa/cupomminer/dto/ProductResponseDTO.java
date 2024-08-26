package com.felipesousa.cupomminer.dto;

import java.math.BigDecimal;

public class ProductResponseDTO {

    private Long ean;
    private String ProductName;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;

    public ProductResponseDTO() {
    }

    public ProductResponseDTO(Long ean, String productName, BigDecimal minPrice, BigDecimal maxPrice) {
        this.ean = ean;
        ProductName = productName;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public Long getEan() {
        return ean;
    }

    public void setEan(Long ean) {
        this.ean = ean;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }
}
