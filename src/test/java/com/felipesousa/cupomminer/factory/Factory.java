package com.felipesousa.cupomminer.factory;

import com.felipesousa.cupomminer.dto.CouponDTO;
import com.felipesousa.cupomminer.dto.ProductDTO;
import com.felipesousa.cupomminer.dto.ProductResponseDTO;
import com.felipesousa.cupomminer.entities.Coupon;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Factory {

    public static Coupon createCouponEntity() {
        Coupon coupon = new Coupon();
        coupon.setCode44("45645416516156156116516515161651561651564564");
        coupon.setPurchaseDate(LocalDate.of(2024, 2, 1));
        coupon.setTotalValue(new BigDecimal("42.50"));
        coupon.setCompanyDocument(54616153156L);
        coupon.setState("SP");
        return coupon;
    }

    public static CouponDTO createCouponDTO() {
        return new CouponDTO("45645416516156156116516515161651561651564564",
                LocalDate.of(2024, 2, 1), new BigDecimal("51.00"), 54616153156L, "SP");
    }

    public static ProductDTO createProductDTO() {
        return new ProductDTO("Sabão Líquido OMO", 4565156131L, new BigDecimal("10.20"), 5);
    }

    public static ProductResponseDTO createObjForRequest() {
        return new ProductResponseDTO(4565156131L, "Sabão Líquido OMO", new BigDecimal("5.00"), new BigDecimal("9.00"));
    }

}
