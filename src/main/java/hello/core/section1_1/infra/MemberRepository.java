package hello.core.section1_1.infra;

import hello.core.section1_1.domain.member.Member;

public interface MemberRepository {
    void register(Member member);
    Member findMember(String memberId);
}
