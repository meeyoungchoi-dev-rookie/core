package hello.core.member.repository;

import hello.core.member.Member;

public interface MemberRepository {

    void register(Member member);

    Member findMember(Long memberId);
}
