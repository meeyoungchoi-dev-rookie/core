package hello.core.section1_3.domain.member.repository;

import hello.core.section1_3.domain.member.entity.Member;
import hello.core.section1_3.domain.member.entity.MemberLevel;

public interface MemberRepository {

    // 회원 가입
    Long register(Member member);

    // 회원 조회
    Member findByMemberId(Long memberSequence);

    // 회원 시퀀스 자동생성
    Long createSequence();

    // 회원 등급 조회
    MemberLevel findMemberLevel(Long memberSequence);
}
