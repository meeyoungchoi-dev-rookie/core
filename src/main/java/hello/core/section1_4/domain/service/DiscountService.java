package hello.core.section1_4.domain.service;

import hello.core.section1_4.domain.entity.Discount;
import hello.core.section1_4.domain.entity.Order;

public interface DiscountService {
    int discount(Order order , Discount discountForVIP);
}
