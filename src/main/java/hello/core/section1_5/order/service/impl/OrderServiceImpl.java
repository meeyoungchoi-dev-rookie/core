package hello.core.section1_5.order.service.impl;

import hello.core.section1_5.discount.DiscountPolicy;
import hello.core.section1_5.discount.impl.FixDiscountPolicy;
import hello.core.section1_5.infra.MemoryMemberRepository;
import hello.core.section1_5.member.Member;
import hello.core.section1_5.member.repository.MemberRepository;
import hello.core.section1_5.order.Order;
import hello.core.section1_5.order.service.OrderService;

public class OrderServiceImpl implements OrderService {

    private MemberRepository memberRepository = new MemoryMemberRepository();

    private DiscountPolicy discountPolicy = new FixDiscountPolicy();

    private static final Integer discountVipPrice = 1000;

    @Override
    public Order makeOrder(Member member, Order order) {
        Member result = memberRepository.findBySequence(member.getMemberSequence());

        Integer discountPrice = 0;
        if (result.checkMemberLevel()) {
            discountPrice = discountPolicy.discount(order , discountVipPrice);
        }

        return new Order(order.getProductName() , order.getProductPrice() , discountPrice);
    }
}
