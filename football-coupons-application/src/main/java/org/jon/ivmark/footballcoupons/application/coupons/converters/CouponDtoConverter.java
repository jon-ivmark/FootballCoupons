package org.jon.ivmark.footballcoupons.application.coupons.converters;

import org.jon.ivmark.footballcoupons.api.coupons.NewCouponDto;
import org.jon.ivmark.footballcoupons.application.coupons.domain.aggregates.Coupon;

import static org.jon.ivmark.footballcoupons.application.coupons.domain.aggregates.Coupon.newCoupon;

public class CouponDtoConverter {
    public Coupon asCoupon(NewCouponDto newCouponDto) {
        return newCoupon(newCouponDto.coupon_name, newCouponDto.coupon_must_be_submitted_before,
                         newCouponDto.number_of_matches);
    }
}