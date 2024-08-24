package com.felipesousa.cupomminer.dto;

import java.time.LocalDate;

public class CouponMinDTO {

    private Long couponId;
    private String buyerName;
    private LocalDate buyerBirthDate;
    private String buyerDocument;

    public CouponMinDTO() {
    }

    public CouponMinDTO(Long couponId, String buyerName, LocalDate buyerBirthDate, String buyerDocument) {
        this.couponId = couponId;
        this.buyerName = buyerName;
        this.buyerBirthDate = buyerBirthDate;
        this.buyerDocument = buyerDocument;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public LocalDate getBuyerBirthDate() {
        return buyerBirthDate;
    }

    public void setBuyerBirthDate(LocalDate buyerBirthDate) {
        this.buyerBirthDate = buyerBirthDate;
    }

    public String getBuyerDocument() {
        return buyerDocument;
    }

    public void setBuyerDocument(String buyerDocument) {
        this.buyerDocument = buyerDocument;
    }
}
