package hello.core.section1_6.discount.impl;

import hello.core.section1_6.discount.DiscountPolicy;
import hello.core.section1_6.member.entity.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    @Override
    public int discount(Member member, int price) {
        if (member.checkMemberLevel()) {
            return 1000;
        }
        return 0;
    }
}
