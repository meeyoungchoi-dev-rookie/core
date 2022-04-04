package hello.core.section1_2.domain.entity;

public class Member {
    private Long sequence = 0L;
    private String memberId;
    private String memberPassword;
    private MemberLevel memberLevel;

    public Member(Long sequence, String memberId, String memberPassword, MemberLevel memberLevel) {
        this.sequence = sequence;
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberLevel = memberLevel;
    }

    public Long getSequence() {
        return sequence;
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

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public void setMemberLevel(MemberLevel memberLevel) {
        this.memberLevel = memberLevel;
    }

    @Override
    public String toString() {
        return "Member{" +
                "sequence=" + sequence +
                ", memberId='" + memberId + '\'' +
                ", memberPassword='" + memberPassword + '\'' +
                ", memberLevel=" + memberLevel +
                '}';
    }
}
