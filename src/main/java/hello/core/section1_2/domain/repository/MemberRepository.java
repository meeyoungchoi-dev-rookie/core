package hello.core.section1_2.domain.repository;

import hello.core.section1_2.domain.entity.Member;

public interface MemberRepository {
    
    // 회원 가입
    Long register(Member member);
    
    
    // 회원 조회
    Member findByMemberId(Long memberSequence);

    // 회원 시퀀스 자동생성
    Long createSequence();
}
