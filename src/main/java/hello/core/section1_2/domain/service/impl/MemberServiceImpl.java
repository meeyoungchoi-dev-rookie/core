package hello.core.section1_2.domain.service.impl;

import hello.core.section1_2.domain.entity.Member;
import hello.core.section1_2.domain.repository.MemberRepository;
import hello.core.section1_2.domain.service.MemberService;
import hello.core.section1_2.infra.MemoryMemberRepository;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public Long register(Member member) {
        Long sequence = createSequence();
        member.setSequence(sequence);
        memberRepository.register(member);
        return sequence;
    }

    @Override
    public Member findByMemberId(Long memberSequence) {
        return memberRepository.findByMemberId(memberSequence);
    }


    @Override
    public Long createSequence() {
        return memberRepository.createSequence();
    }
}
