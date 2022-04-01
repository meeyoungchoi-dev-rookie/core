package hello.core.domain.policy;

import hello.core.domain.member.Member;

public interface DefaultPolicy {
    boolean isSatisfied(Member member); // 할인 대상자인지 판단
    Integer discountPrice(); // 할인 대상자인 경우 할인 적용
}
