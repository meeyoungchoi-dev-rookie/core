package hello.core.section1_5.member;

public class Member {
    private Long memberSequence;
    private String memberId;
    private String memberPassword;
    private MemberLevel memberLevel;

    public Member(Long memberSequence, String memberId, String memberPassword, MemberLevel memberLevel) {
        this.memberSequence = memberSequence;
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberLevel = memberLevel;
    }

    public Long getMemberSequence() {
        return memberSequence;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public MemberLevel getMemberLevel() {
        return memberLevel;
    }

    // 회원 등급 조회
    public boolean checkMemberLevel() {
        if (this.getMemberLevel() == MemberLevel.VIP) {
            return true;
        }
        return false;
    }
}
