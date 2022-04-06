package hello.core.section1_5.discount;

import hello.core.section1_5.order.Order;

public interface DiscountPolicy {
    Integer discount(Order order, Integer discountPrice);
}
