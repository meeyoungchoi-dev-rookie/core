package hello.core.section1_4.domain.repository;

import hello.core.section1_4.domain.entity.Discount;
import hello.core.section1_4.domain.entity.Order;

public interface DiscountPolicy {

    // VIP 할인
    int discount(Order order , Discount discountForVip);
}
