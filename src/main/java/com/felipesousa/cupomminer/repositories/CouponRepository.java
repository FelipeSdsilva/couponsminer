package com.felipesousa.cupomminer.repositories;

import com.felipesousa.cupomminer.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    Optional<Coupon> findByCode44(String code44);
}
