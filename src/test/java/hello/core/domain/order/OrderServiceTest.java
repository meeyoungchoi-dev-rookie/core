package hello.core.domain.order;

import hello.core.section1_6.member.entity.Member;
import hello.core.section1_6.member.entity.MemberLevel;
import hello.core.section1_6.order.entity.Order;
import hello.core.section1_6.order.service.OrderService;
import hello.core.section1_6.order.service.impl.OrderServiceImpl;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    private final OrderService orderService = new OrderServiceImpl();

    @Test
    public void makeOrder() {
        // 회원 객체 생성
        Member member = new Member(null, "userId1", "userPassword1", MemberLevel.VIP);

        // 주문 생성
        Order order = new Order("product1", 2, 20000, 0, 0);

        // 회원 등급에따른 할인 금액 판단 및 주문 진행 및 결화 확인
        Order result = orderService.makeOrder(order, member);
        Assertions.assertThat(result.getDiscountPrice()).isEqualTo(39000);
    }
}
