package hello.core.order.service.impl;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.repository.MemberRepository;
import hello.core.member.repository.impl.MemoryMemberRepository;
import hello.core.order.Order;
import hello.core.order.service.OrderService;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        System.out.println(memberId + " " + itemName + " " + itemPrice );
        Member member = memberRepository.findMember(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
