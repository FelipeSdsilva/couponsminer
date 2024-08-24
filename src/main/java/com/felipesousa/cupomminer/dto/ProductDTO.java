package com.felipesousa.cupomminer.dto;

import java.math.BigDecimal;

public class ProductDTO {

    private String name;
    private Long ean;
    private BigDecimal unitaryPrice;
    private Integer quantity;

    public ProductDTO() {
    }

    public ProductDTO(String name, Long ean, BigDecimal unitaryPrice, Integer quantity) {
        this.name = name;
        this.ean = ean;
        this.unitaryPrice = unitaryPrice;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getEan() {
        return ean;
    }

    public void setEan(Long ean) {
        this.ean = ean;
    }

    public BigDecimal getUnitaryPrice() {
        return unitaryPrice;
    }

    public void setUnitaryPrice(BigDecimal unitaryPrice) {
        this.unitaryPrice = unitaryPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
