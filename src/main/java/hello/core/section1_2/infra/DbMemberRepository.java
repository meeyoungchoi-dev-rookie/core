package hello.core.section1_2.infra;

import hello.core.section1_2.domain.entity.Member;
import hello.core.section1_2.domain.repository.MemberRepository;

public class DbMemberRepository implements MemberRepository {
    @Override
    public Long register(Member member) {
        return 0L;
    }

    @Override
    public Member findByMemberId(Long memberSequence) {
        return null;
    }


    @Override
    public Long createSequence() {
        return null;
    }
}
