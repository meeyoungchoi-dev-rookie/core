package hello.core.section1_5.member.repository;


import hello.core.section1_5.member.Member;

public interface MemberRepository {

    Long register(Member member);

    Member findBySequence(Long sequence);
}
