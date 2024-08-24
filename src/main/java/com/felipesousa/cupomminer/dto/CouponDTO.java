package com.felipesousa.cupomminer.dto;

import com.felipesousa.cupomminer.services.ValidDoc;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CouponDTO {

    @NotBlank
    @Pattern(regexp = "\\d{44}", message = "code44 must contain exactly 44 digits")
    private String code44;
    private LocalDate purchaseDate;
    private BigDecimal totalValue;

    @ValidDoc(message = "Document invalid!")
    private Long companyDocument;
    private String state;

    private List<ProductDTO> products = new ArrayList<>();

    public CouponDTO() {
    }

    public CouponDTO(String code44, LocalDate purchaseDate, BigDecimal totalValue, Long companyDocument, String state) {
        this.code44 = code44;
        this.purchaseDate = purchaseDate;
        this.totalValue = totalValue;
        this.companyDocument = companyDocument;
        this.state = state;
    }


    public String getCode44() {
        return code44;
    }

    public void setCode44(String code44) {
        this.code44 = code44;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public Long getCompanyDocument() {
        return companyDocument;
    }

    public void setCompanyDocument(Long companyDocument) {
        this.companyDocument = companyDocument;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }
}
