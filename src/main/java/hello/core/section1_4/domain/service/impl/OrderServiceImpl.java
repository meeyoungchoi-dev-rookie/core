package hello.core.section1_4.domain.service.impl;

import hello.core.section1_4.domain.entity.Discount;
import hello.core.section1_4.domain.entity.Member;
import hello.core.section1_4.domain.entity.Order;
import hello.core.section1_4.domain.repository.DiscountPolicy;
import hello.core.section1_4.domain.service.OrderService;
import hello.core.section1_4.infra.FixDiscountPolicy;

public class OrderServiceImpl implements OrderService {

    private final static DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public void makeOrder(Order order , Discount discountForVip) {
        Member member = order.getMember();
        boolean status = member.checkMemberLevel();
        if (status) {
            discountPolicy.discount(order, discountForVip);
        }
    }
}
