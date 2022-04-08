package hello.core.member;

public class Member {
    private Long sequence;
    private String memberId;
    private Grade grade;

    public Member(Long sequence, String memberId, Grade grade) {
        this.sequence = sequence;
        this.memberId = memberId;
        this.grade = grade;
    }

    public String getMemberId() {
        return memberId;
    }

    public Grade getGrade() {
        return grade;
    }

    public Long getSequence() {
        return sequence;
    }
}
