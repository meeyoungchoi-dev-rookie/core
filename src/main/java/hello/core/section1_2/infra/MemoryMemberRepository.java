package hello.core.section1_2.infra;

import hello.core.section1_2.domain.entity.Member;
import hello.core.section1_2.domain.repository.MemberRepository;

import java.util.concurrent.ConcurrentHashMap;

public class MemoryMemberRepository implements MemberRepository {

    // 실무에서는 HashMap을 사용하게 되면 동시성 문제가 발생할 수 있다
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
}
