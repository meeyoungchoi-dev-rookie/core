package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.service.MemberService;
import hello.core.order.Order;
import hello.core.order.service.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        Order order = new Order(member.getSequence(), "itemA", 20000, 0);
        memberService.register(member);
        Member finded = memberService.findMember(memberId);
        Order ordered = orderService.createOrder(finded.getSequence(), order.getItemName() , order.getItemPrice());

        System.out.println(ordered.getDiscountPrice());

    }
}
