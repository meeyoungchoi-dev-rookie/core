package hello.core.section1_5.discount.impl;

import hello.core.section1_5.discount.DiscountPolicy;
import hello.core.section1_5.member.Member;

public class RateDiscountPolicy implements DiscountPolicy {
    @Override
    public Long discount(Member member, Long discountPrice) {
        return null;
    }
}
