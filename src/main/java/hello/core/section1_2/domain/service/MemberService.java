package hello.core.section1_2.domain.service;

import hello.core.section1_2.domain.entity.Member;

public interface MemberService {

    // 회원 가입
   Long register(Member member);

    // 회원 조회
   Member findByMemberId(Long memberSequence);

   // 시퀀스 생성
    Long createSequence();
}
