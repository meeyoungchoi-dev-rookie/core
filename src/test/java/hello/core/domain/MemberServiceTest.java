package hello.core.domain;

import hello.core.section1_2.domain.entity.Member;
import hello.core.section1_2.domain.entity.MemberLevel;
import hello.core.section1_2.domain.service.MemberService;
import hello.core.section1_2.domain.service.impl.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    private  final MemberService memberService = new MemberServiceImpl();

    @Test
    void register() {

        // given
        Member member = new Member(null, "id1", "pw1", MemberLevel.BASIC);

        // when
        Long memberSequence = memberService.register(member);
        member.setSequence(memberSequence);

        Member result = memberService.findByMemberId(memberSequence);

        // then
        Assertions.assertEquals(memberSequence, result.getSequence());
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    void findByMemberId() {

        // given
        Member member = new Member(null, "id1", "pw1", MemberLevel.BASIC);
        Member member2 = new Member(null, "id2", "pw2", MemberLevel.VIP);

        // when
        Long member1Sequence = memberService.register(member);
        Long member2sequence = memberService.register(member2);
        member.setSequence(member1Sequence);
        member2.setSequence(member2sequence);

        Member member1Result = memberService.findByMemberId(member1Sequence);
        Member member2Result = memberService.findByMemberId(member2sequence);

        // then
        Assertions.assertEquals(member, member1Result);
        Assertions.assertEquals(member2, member2Result);
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(member1Result);
        org.assertj.core.api.Assertions.assertThat(member2).isEqualTo(member2Result);
    }
}
