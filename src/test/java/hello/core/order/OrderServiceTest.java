package hello.core.order;


import hello.core.AppConfig;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.repository.MemberRepository;
import hello.core.member.repository.impl.MemoryMemberRepository;
import hello.core.member.service.MemberService;
import hello.core.member.service.impl.MemberServiceImpl;
import hello.core.order.service.OrderService;
import hello.core.order.service.impl.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        orderService = appConfig.orderService();
        memberService = appConfig.memberService();
    }


    @Test
    void  createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.register(member);

        Order order = orderService.createOrder(memberId , "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

    @Test
    void fieldInjectionTest() {


        // nullPointerException이 터진다
        // 왜?
        //createOrder 메서드는 MemberRepository에 의존하고 있어 MemoryMemberRepository 구현체가필요하다
        // 필드 의존주입의 경우
        // 의존관계를 바꾸는 것이 어렵다
        //결국 set 메서드를 사용할 수 밖에 없다
//        orderService.setMemberRepository(new MemoryMemberRepository());
//        orderService.setDiscountPolicy(new FixDiscountPolicy());
//
//        orderService
//                .createOrder(1L, "itemA", 10000);

    }


}
