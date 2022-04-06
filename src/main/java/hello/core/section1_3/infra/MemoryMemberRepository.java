package hello.core.section1_3.infra;


import hello.core.section1_3.domain.member.entity.Member;
import hello.core.section1_3.domain.member.entity.MemberLevel;
import hello.core.section1_3.domain.member.repository.MemberRepository;

import java.util.concurrent.ConcurrentHashMap;

public class MemoryMemberRepository implements MemberRepository {
    private static ConcurrentHashMap<Long , Member> memberMap = new ConcurrentHashMap<>();

    private static Long sequence = 0L;

    @Override
    public Long register(Member member) {
        memberMap.put(member.getSequence() , member);
        return member.getSequence();
    }

    @Override
    public Member findByMemberId(Long memberSequence) {
        return memberMap.get(memberSequence);
    }

    @Override
    public Long createSequence() {
        return sequence+=1;
    }

    @Override
    public MemberLevel findMemberLevel(Long memberSequence) {
        Member member = memberMap.get(memberSequence);
        return member.getMemberLevel();
    }
}
