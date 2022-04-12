package hello.core.member.service;

import hello.core.member.Member;

public interface MemberService {
    void register(Member member);
    Member findMember(Long memberId);
}
