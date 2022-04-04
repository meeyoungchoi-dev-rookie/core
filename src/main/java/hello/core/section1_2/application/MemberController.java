package hello.core.section1_2.application;

import hello.core.section1_2.domain.entity.Member;
import hello.core.section1_2.domain.entity.MemberLevel;
import hello.core.section1_2.domain.service.MemberService;
import hello.core.section1_2.domain.service.impl.MemberServiceImpl;

public class MemberController {

    public static void main(String[] args) {

        MemberService memberService = new MemberServiceImpl();

        Member member1 = new Member(null, "test1", "password1", MemberLevel.BASIC);

        Long sequence = memberService.register(member1);
        Member findOne = memberService.findByMemberId(sequence);
        System.out.println(member1.equals(findOne));

        Member member2 = new Member(null, "tes2", "password2", MemberLevel.VIP);
        sequence = memberService.register(member2);
        Member findTwo = memberService.findByMemberId(sequence);
        System.out.println(member2.equals(findTwo));
    }
}
