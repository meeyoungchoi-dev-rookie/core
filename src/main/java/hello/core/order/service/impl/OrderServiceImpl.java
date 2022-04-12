package hello.core.order.service.impl;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.repository.MemberRepository;
import hello.core.order.Order;
import hello.core.order.service.OrderService;

public class OrderServiceImpl implements OrderService {

    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        System.out.println(memberId + " " + itemName + " " + itemPrice );
        Member member = memberRepository.findMember(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
