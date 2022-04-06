package hello.core.section1_3.infra;

import hello.core.section1_3.domain.discount.entity.Order;
import hello.core.section1_3.domain.discount.repository.DiscountPolicy;
import hello.core.section1_4.domain.entity.Discount;

public class FixDiscountPolicy implements DiscountPolicy {

    @Override
    public int discount(Order order, Discount discountForVIP) {
        return 0;
    }
}
