package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.service.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {


        //AppConfig 환경설정 정보를 바탕으로 @Bean 붙은 애들을 객체를 생성하여 스프링 컨테이너가 관리해 준다
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //                            꺼낼 빈이름 (기본적으로 메서드 이름) => 해당 bean 객체를 찾겠다 , 반환타입
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);


        Member member = new Member(1L, "a", Grade.VIP);
        memberService.register(member);

        Member findMember = memberService.findMember(member.getSequence());
    }
}
