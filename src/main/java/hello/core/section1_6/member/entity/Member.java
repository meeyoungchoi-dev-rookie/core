package hello.core.section1_6.member.entity;

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

    // 회원 등급 검사
    public boolean checkMemberLevel() {
        if (this.getMemberLevel() == MemberLevel.VIP) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberSequence=" + memberSequence +
                ", memberId='" + memberId + '\'' +
                ", memberPassword='" + memberPassword + '\'' +
                '}';
    }
}
