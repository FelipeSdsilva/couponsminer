package com.felipesousa.cupomminer.services;

import com.felipesousa.cupomminer.config.rabbitmq.RabbitMQConfig;
import com.felipesousa.cupomminer.dto.CouponDTO;
import com.felipesousa.cupomminer.dto.CouponMinDTO;
import com.felipesousa.cupomminer.dto.ProductDTO;
import com.felipesousa.cupomminer.dto.ProductResponseDTO;
import com.felipesousa.cupomminer.entities.Coupon;
import com.felipesousa.cupomminer.exceptions.TotalValueException;
import com.felipesousa.cupomminer.repositories.CouponRepository;
import com.felipesousa.cupomminer.requests.ProductRequest;
import com.felipesousa.cupomminer.utils.DocFormat;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Transactional
    public CouponMinDTO saveCoupon(CouponDTO coupon) throws Exception {

        Coupon couponEntity = new Coupon();
        converterDtoInEntity(coupon, couponEntity);

        BigDecimal totalValue = new BigDecimal("0");

        totalValue = validateProducts(coupon.getProducts(), totalValue);

        if (!totalValue.equals(coupon.getTotalValue()))
            throw new TotalValueException("The total value of the coupon does not match the sum of the product values!");

        couponEntity = couponRepository.save(couponEntity);

        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, couponEntity);

        String doc = DocFormat.formatDOc(couponEntity.getCompanyDocument());

        return new CouponMinDTO(couponEntity.getId(), "Lucas Souza", couponEntity.getPurchaseDate(), doc);
    }

    @Transactional
    @RabbitListener(queues = "coupon-queue")
    public void handleCouponUpdate(CouponDTO message) {
        Coupon coupon = couponRepository.findByCode44(message.getCode44())
                .orElseThrow(() -> new RuntimeException("Coupon not found"));
        converterDtoInEntity(message, coupon);
        couponRepository.save(coupon);
    }

    protected BigDecimal validateProducts(List<ProductDTO> products, BigDecimal totalValue) throws Exception {
        for (ProductDTO product : products) {
            totalValue = totalValue.add(product.getUnitaryPrice().multiply(new BigDecimal(product.getQuantity())));

            ProductResponseDTO productResponse = ProductRequest.getProduct(product.getEan().toString());

            if (productResponse == null ||
                    product.getUnitaryPrice().compareTo(productResponse.getMinPrice()) < 0 ||
                    product.getUnitaryPrice().compareTo(productResponse.getMaxPrice()) > 0) {
                throw new IllegalArgumentException("Invalid product price for EAN: " + product.getEan());
            }
        }
        return totalValue;
    }


    private void converterDtoInEntity(CouponDTO coupon, Coupon couponEntity) {
        couponEntity.setCode44(coupon.getCode44());
        couponEntity.setPurchaseDate(coupon.getPurchaseDate());
        couponEntity.setTotalValue(coupon.getTotalValue());
        couponEntity.setCompanyDocument(coupon.getCompanyDocument());
        couponEntity.setState(coupon.getState());
    }
}
