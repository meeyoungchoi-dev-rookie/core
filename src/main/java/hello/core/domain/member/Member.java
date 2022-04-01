package hello.core.domain.member;

public class Member {
    private String userName;
    private String userId;
    private String userPassword;

    private Rating rate;

    public Member(String userName, String userId, String userPassword, Rating rate) {
        this.userName = userName;
        this.userId = userId;
        this.userPassword = userPassword;
        this.rate = rate;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public Rating getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return "Member{" +
                "userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", rate=" + rate +
                '}';
    }
}
