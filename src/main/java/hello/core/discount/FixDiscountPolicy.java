package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;


public class FixDiscountPolicy implements DiscountPolicy{

    private int DiscountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return DiscountFixAmount;
        }else {
            return 0;
        }
    }
}
