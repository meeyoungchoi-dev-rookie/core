package hello.core.section1_1.repository;

import hello.core.section1_1.domain.member.Member;
import hello.core.section1_1.infra.MemberRepository;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository  implements MemberRepository {

    public static Map<String , Member> memberMap = new HashMap<>();
    public static Long memberNo = 0L;

    @Override
    public void register(Member member) {
        if (checkDuplicateId(member.getUserId()) == false) {
            memberMap.put(member.getUserId() , member);
        }
    }

    @Override
    public Member findMember(String memberId) {
        return memberMap.get(memberId);
    }

    // 아이디 중복 검사
    public boolean checkDuplicateId(String memberId) {
        if (MemoryMemberRepository.memberMap.get(memberId) != null) {
            return true;
        }
        return false;
    }

}
