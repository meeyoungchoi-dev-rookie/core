package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.repository.impl.MemoryMemberRepository;
import hello.core.order.service.impl.OrderServiceImpl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


public class OrderServiceImplTest {

    @Test
    void creatOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.register(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
