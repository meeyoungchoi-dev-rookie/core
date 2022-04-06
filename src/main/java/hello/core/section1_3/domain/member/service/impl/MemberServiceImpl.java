package hello.core.section1_3.domain.member.service.impl;

import hello.core.section1_3.domain.member.entity.Member;
import hello.core.section1_3.domain.member.repository.MemberRepository;
import hello.core.section1_3.domain.member.service.MemberService;
import hello.core.section1_3.infra.MemoryMemberRepository;

public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public Long insertMember(Member member) {
        return memberRepository.register(member);
    }

    @Override
    public Member findMember(Long memberSequence) {
        return memberRepository.findByMemberId(memberSequence);
    }
}
