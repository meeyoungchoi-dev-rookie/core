package hello.core.infra;

import hello.core.domain.member.Member;

public interface MemberRepository {
    void register(Member member);
    Member findMember(String memberId);
}
