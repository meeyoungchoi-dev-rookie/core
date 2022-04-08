package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.repository.MemberRepository;
import hello.core.member.repository.impl.MemoryMemberRepository;
import hello.core.order.Order;
import hello.core.order.service.OrderService;
import hello.core.order.service.impl.OrderServiceImpl;


public class OrderApp {
    public static void main(String[] args) {

        MemberRepository memberRepository = new MemoryMemberRepository();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        Order order = new Order(member.getSequence(), "itemA", 5000, 0);
        memberRepository.register(member);
      Member finded = memberRepository.findMember(memberId);
        Order ordered = orderService.createOrder(finded.getSequence(), order.getItemName() , order.getItemPrice());

        System.out.println(ordered.calculatePrice());

    }
}
