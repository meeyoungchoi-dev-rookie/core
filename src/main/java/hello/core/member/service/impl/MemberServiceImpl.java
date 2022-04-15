package hello.core.member.service.impl;

import hello.core.member.Member;
import hello.core.member.repository.MemberRepository;

import hello.core.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    // 자동 의존관계 주입
    // (ac.getBean(MemberRepository.class))
    @Autowired
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

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
