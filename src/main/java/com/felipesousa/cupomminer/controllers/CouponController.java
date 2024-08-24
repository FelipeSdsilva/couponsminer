package com.felipesousa.cupomminer.controllers;


import com.felipesousa.cupomminer.dto.CouponDTO;
import com.felipesousa.cupomminer.dto.CouponMinDTO;
import com.felipesousa.cupomminer.services.CouponService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(value = "/coupons")
@Tag(name = "Coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping
    @Operation(description = "This endpoint included new coupon in the system if valid")
    public ResponseEntity<CouponMinDTO> postNewCoupon(@Valid @RequestBody CouponDTO couponDTO) {
        CouponMinDTO minDTO = couponService.saveCoupon(couponDTO);
        var uri = fromCurrentRequest().path("/{id}").buildAndExpand(minDTO.getCouponId()).toUri();
        return ResponseEntity.created(uri).body(null);
    }

}
