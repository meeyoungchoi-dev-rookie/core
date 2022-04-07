package hello.core.section1_6.discount;

import hello.core.section1_6.member.entity.Member;
import hello.core.section1_6.order.entity.Order;

public interface DiscountPolicy {
    // 회원할인 가능한 등급인 경우 할인 진행
    // 총 금액에서 얼마가 할인되었는지 반환해 준다 (price: 총금액)
    int discount(Member member , int price);

}
