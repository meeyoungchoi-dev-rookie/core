package hello.core.section1_4.domain.service.impl;


import hello.core.section1_4.domain.entity.Discount;
import hello.core.section1_4.domain.entity.Order;
import hello.core.section1_4.domain.repository.DiscountPolicy;
import hello.core.section1_4.domain.service.DiscountService;
import hello.core.section1_4.infra.RateDiscountPolicy;

public class DiscountServiceImpl implements DiscountService {

    private DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Override
    public int discount(Order order, Discount discountForVIP) {
        return discountPolicy.discount(order, discountForVIP);
    }
}
