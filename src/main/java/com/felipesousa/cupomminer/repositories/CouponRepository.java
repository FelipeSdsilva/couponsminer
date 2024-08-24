package com.felipesousa.cupomminer.repositories;

import com.felipesousa.cupomminer.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
