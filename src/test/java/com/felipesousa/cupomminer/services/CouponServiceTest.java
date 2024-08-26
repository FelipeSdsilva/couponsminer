package com.felipesousa.cupomminer.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.felipesousa.cupomminer.dto.CouponDTO;
import com.felipesousa.cupomminer.dto.ProductDTO;
import com.felipesousa.cupomminer.dto.ProductResponseDTO;
import com.felipesousa.cupomminer.entities.Coupon;
import com.felipesousa.cupomminer.exceptions.TotalValueException;
import com.felipesousa.cupomminer.repositories.CouponRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static com.felipesousa.cupomminer.factory.Factory.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CouponServiceTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private CouponService couponService;

    @Mock
    private CouponRepository couponRepository;

    @Mock
    private ObjectMapper objectMapper;

    private ProductDTO product;
    private CouponDTO couponDTO;
    private Coupon coupon;
    private ProductResponseDTO mockResponse;
    private String ean;
    private BigDecimal wrongTotalValue;

    @BeforeEach
    void setUp() {
        product = createProductDTO();
        couponDTO = createCouponDTO();
        coupon = createCouponEntity();
        mockResponse = createObjForRequest();
        ean = product.getEan().toString();
        wrongTotalValue = new BigDecimal("100.00");
    }

    @Test
    public void validateProductsShouldThrowIllegalArgumentExceptionWhenValueIsOutOfRange() {
        product.setUnitaryPrice(new BigDecimal("100"));
        List<ProductDTO> products = Collections.singletonList(product);


        assertThrows(IllegalArgumentException.class, () -> {
            couponService.validateProducts(products, BigDecimal.ZERO);
        });
    }

    @Test
    public void saveCouponShouldThrowTotalValueExceptionWhenTotalValueDoesNotMatchSum() {
        couponDTO.setTotalValue(wrongTotalValue);

        assertThrows(TotalValueException.class, () -> {
            couponService.saveCoupon(couponDTO);
        });
    }

    @Test
    public void saveCouponShouldPublishToRabbitMQ() throws Exception {
        product.setUnitaryPrice(new BigDecimal("7.00"));
        couponDTO.getProducts().add(product);
        couponDTO.setTotalValue(product.getUnitaryPrice().multiply(BigDecimal.valueOf(product.getQuantity())));

        when(couponRepository.save(any())).thenReturn(coupon);

        couponService.saveCoupon(couponDTO);

        verify(rabbitTemplate).convertAndSend(anyString(), anyString(), ArgumentMatchers.eq(coupon));
    }
}