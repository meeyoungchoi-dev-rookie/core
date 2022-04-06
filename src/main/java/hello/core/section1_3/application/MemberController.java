package hello.core.section1_3.application;



import hello.core.section1_3.domain.member.entity.Member;
import hello.core.section1_3.domain.member.entity.MemberLevel;
import hello.core.section1_3.domain.member.service.MemberService;
import hello.core.section1_3.domain.member.service.impl.MemberServiceImpl;

public class MemberController {
    public static void main(String[] args) {


        MemberService memberService = new MemberServiceImpl();

        Member member1 = new Member(null, "test1", "password1", MemberLevel.BASIC);

        Long sequence = memberService.insertMember(member1);
        Member findOne = memberService.findMember(sequence);

        Member member2 = new Member(null, "tes2", "password2", MemberLevel.VIP);
        sequence = memberService.insertMember(member2);
        Member findTwo = memberService.findMember(sequence);



    }
}
