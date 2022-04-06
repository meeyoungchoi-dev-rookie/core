package hello.core.section1_5.infra;

import hello.core.section1_5.member.Member;
import hello.core.section1_5.member.repository.MemberRepository;

import java.util.concurrent.ConcurrentHashMap;

public class MemoryMemberRepository implements MemberRepository {

    private final static ConcurrentHashMap<Long , Member> memberMap = new ConcurrentHashMap<>();
    private static Long memberSequence = 0L;

    @Override
    public Long register(Member member) {
        Long sequence = memberSequence += 1;
        memberMap.put(sequence,  member);
        return sequence;
    }

    @Override
    public Member findBySequence(Long sequence) {
        return memberMap.get(sequence);
    }
}
