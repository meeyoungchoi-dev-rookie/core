package hello.core.section1_4.domain.entity;

public class Member {
    private Long memberSequence;
    private String userId;
    private String userPassword;
    private MemberLevel memberLevel;

    public Member(Long memberSequence, String userId, String userPassword, MemberLevel memberLevel) {
        this.memberSequence = memberSequence;
        this.userId = userId;
        this.userPassword = userPassword;
        this.memberLevel = memberLevel;
    }

    public Long getMemberSequence() {
        return memberSequence;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public MemberLevel getMemberLevel() {
        return memberLevel;
    }

    // 할인시 회원 레벨 검사
    public boolean checkMemberLevel() {
        if (this.getMemberLevel().equals("VIP") ) {
             return true;
        } else {
            return false;
        }
    }
}
