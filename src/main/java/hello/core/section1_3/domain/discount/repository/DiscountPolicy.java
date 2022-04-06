package hello.core.section1_3.domain.discount.repository;

import hello.core.section1_3.domain.discount.entity.Order;
import hello.core.section1_4.domain.entity.Discount;

public interface DiscountPolicy {
    int discount(Order order, Discount discountForVIP);

}
