package hello.core.member.service.impl;

import hello.core.member.Member;
import hello.core.member.repository.MemberRepository;
import hello.core.member.repository.impl.MemoryMemberRepository;
import hello.core.member.service.MemberService;

public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void register(Member member) {
        memberRepository.register(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findMember(memberId);
    }
}
