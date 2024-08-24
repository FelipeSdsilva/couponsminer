package com.felipesousa.cupomminer.services;

import com.felipesousa.cupomminer.dto.CouponDTO;
import com.felipesousa.cupomminer.dto.CouponMinDTO;
import com.felipesousa.cupomminer.dto.ProductDTO;
import com.felipesousa.cupomminer.entities.Coupon;
import com.felipesousa.cupomminer.exceptions.TotalValueException;
import com.felipesousa.cupomminer.repositories.CouponRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Transactional
    public CouponMinDTO saveCoupon(CouponDTO coupon) {

        Coupon couponEntity = new Coupon();
        converterDtoInEntity(coupon, couponEntity);

        BigDecimal totalValue = new BigDecimal("0");

        for (ProductDTO product : coupon.getProducts()) {
            totalValue = totalValue.add(product.getUnitaryPrice().multiply(new BigDecimal(product.getQuantity())));
        }
        if (!totalValue.equals(couponEntity.getTotalValue()))
            throw new TotalValueException("The total value of the coupon does not match the sum of the product values!");

        couponEntity = couponRepository.save(couponEntity);


        return new CouponMinDTO(couponEntity.getId(), "", couponEntity.getPurchaseDate(), "");
    }

    private void converterDtoInEntity(CouponDTO coupon, Coupon couponEntity) {
        couponEntity.setCode44(coupon.getCode44());
        couponEntity.setPurchaseDate(coupon.getPurchaseDate());
        couponEntity.setTotalValue(coupon.getTotalValue());
        couponEntity.setCompanyDocument(coupon.getCompanyDocument());
        couponEntity.setState(coupon.getState());
    }
}
