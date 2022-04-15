package hello.core.member.repository.impl;

import hello.core.member.Member;
import hello.core.member.repository.MemberRepository;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class MemoryMemberRepository implements MemberRepository {
    private static ConcurrentHashMap<Long , Member> memberConcurrentHashMap = new ConcurrentHashMap<>();
    private static Long sequence = 0L;

    @Override
    public void register(Member member) {
        memberConcurrentHashMap.put(sequence+=1 , member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberConcurrentHashMap.get(memberId);
    }
}
