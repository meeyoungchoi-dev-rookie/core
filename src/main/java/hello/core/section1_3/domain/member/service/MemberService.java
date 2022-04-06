package hello.core.section1_3.domain.member.service;

import hello.core.section1_3.domain.member.entity.Member;

public interface MemberService {

    // 회원 저장
    Long insertMember(Member member);
    
    // 회원 조회
    Member findMember(Long memberSequence);




}
