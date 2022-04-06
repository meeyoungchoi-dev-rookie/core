package hello.core.section1_5.discount.impl;

import hello.core.section1_5.discount.DiscountPolicy;
import hello.core.section1_5.infra.MemoryMemberRepository;
import hello.core.section1_5.member.repository.MemberRepository;
import hello.core.section1_5.order.Order;

public class FixDiscountPolicy implements DiscountPolicy {

    private MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public Integer discount(Order order, Integer discountPrice) {
        return order.getOrderTotalPrice() - discountPrice;
    }
}
