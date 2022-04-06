package hello.core.section1_4.infra;

import hello.core.section1_4.domain.entity.Discount;
import hello.core.section1_4.domain.entity.Order;
import hello.core.section1_4.domain.repository.DiscountPolicy;

public class RateDiscountPolicy implements DiscountPolicy {

    @Override
    public int discount(Order order, Discount discountForVip) {
        return ((order.getProductPrice() * order.getProductCount()) - discountForVip.getDiscountForVip());
    }
}
