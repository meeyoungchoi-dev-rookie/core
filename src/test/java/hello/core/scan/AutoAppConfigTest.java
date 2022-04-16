package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.repository.impl.MemoryMemberRepository;
import hello.core.member.service.MemberService;

import hello.core.order.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);

        OrderServiceImpl orderService = ac.getBean(OrderServiceImpl.class);
        ac.getBean(MemoryMemberRepository.class);
        System.out.println(orderService);
        //System.out.println(memberRepository);

    }
}
