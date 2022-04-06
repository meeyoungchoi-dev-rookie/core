package hello.core.section1_3.infra;

import hello.core.section1_3.domain.discount.entity.Order;
import hello.core.section1_3.domain.discount.repository.DiscountPolicy;

public class RateDiscountPolicy implements DiscountPolicy {

    @Override
    public int discount(Order order) {
        return order.getProductPrice();
    }

    @Override
    public int VIPDiscount(Order order) {
        return (int) ( order.getProductPrice() * 0.10 );
    }
}
