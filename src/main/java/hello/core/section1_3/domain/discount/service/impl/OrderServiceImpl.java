package hello.core.section1_3.domain.discount.service.impl;


import hello.core.section1_3.domain.discount.entity.Order;

import hello.core.section1_3.domain.discount.repository.DiscountPolicy;
import hello.core.section1_3.domain.discount.service.OrderService;
import hello.core.section1_3.domain.member.entity.MemberLevel;
import hello.core.section1_3.domain.member.repository.MemberRepository;
import hello.core.section1_3.infra.MemoryMemberRepository;
import hello.core.section1_3.infra.RateDiscountPolicy;

public class OrderServiceImpl implements OrderService {

    private MemberRepository memberRepository = new MemoryMemberRepository();

    private DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Override
    public MemberLevel findMemberLevel(Long memberSequence) {
        return memberRepository.findMemberLevel(memberSequence);
    }

    @Override
    public int VIPDiscount(Order order) {
        return discountPolicy.discount(order);
    }

    @Override
    public int NoneDiscount(Order order) {
        return discountPolicy.discount(order);
    }


}
