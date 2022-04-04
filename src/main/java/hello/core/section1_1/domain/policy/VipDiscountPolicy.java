package hello.core.section1_1.domain.policy;

import hello.core.section1_1.domain.member.Member;
import hello.core.section1_1.domain.member.Rating;
import hello.core.section1_1.domain.order.Order;
import hello.core.section1_1.repository.MemoryOrderRepositoryVer2;

import java.util.List;


public class VipDiscountPolicy implements DefaultPolicy {
    @Override
    public boolean isSatisfied(Member member) {

        if (member.getRate().getRate().equals("VIP")) {
            return true;
        };
        return false;
    }

    @Override
    public Integer discountPrice() {
        List<Order> orderList = null;

        double discountPrice = 0;
        // 모든 vip 천원 할인
        if (isSatisfied(new Member("hong","testUser", "1234" , new Rating("true"))) == true) {
            Long orderNo = MemoryOrderRepositoryVer2.orderNo;
            orderList = MemoryOrderRepositoryVer2.orders.get(orderNo);
        }

        for (Order data : orderList) {
            discountPrice = data.getTotalPrice() - (data.getTotalPrice() * 0.10);
        }

        return (int) discountPrice;
    }
}
