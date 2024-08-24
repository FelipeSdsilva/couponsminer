package com.felipesousa.cupomminer.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code44;
    private LocalDate purchaseDate;
    private BigDecimal totalValue;
    private Long companyDocument;
    private String state;

    public Coupon() {
    }

    public Coupon(Long id, String code44, LocalDate purchaseDate, BigDecimal totalValue, Long companyDocument, String state) {
        this.id = id;
        this.code44 = code44;
        this.purchaseDate = purchaseDate;
        this.totalValue = totalValue;
        this.companyDocument = companyDocument;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
