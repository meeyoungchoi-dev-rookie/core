package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.repository.MemberRepository;
import hello.core.member.repository.impl.MemoryMemberRepository;
import hello.core.member.service.MemberService;
import hello.core.member.service.impl.MemberServiceImpl;
import hello.core.order.service.OrderService;
import hello.core.order.service.impl.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // 스프링 컨테이너에 등록된다
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository() , discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {

        return new RateDiscountPolicy();
    }



}
