package hello.core.section1_3.domain.member.entity;

public class Member {
    private Long sequence = 0L;
    private String userId;
    private String userPassword;
    private MemberLevel memberLevel;

    public Member(Long sequence, String userId, String userPassword, MemberLevel memberLevel) {
        this.sequence = sequence;
        this.userId = userId;
        this.userPassword = userPassword;
        this.memberLevel = memberLevel;
    }

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public MemberLevel getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(MemberLevel memberLevel) {
        this.memberLevel = memberLevel;
    }

    // 회원 등급 검사
    public MemberLevel checkMemberLevel() {
        if (this.getMemberLevel() == MemberLevel.BASIC) {
            return MemberLevel.BASIC;
        } else {
            return MemberLevel.VIP;
        }
    }




    @Override
    public String toString() {
        return "Member{" +
                "sequence=" + sequence +
                ", userId='" + userId + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", memberLevel=" + memberLevel +
                '}';
    }
}

