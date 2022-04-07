package hello.core.section1_6.order.service;

import hello.core.section1_6.member.entity.Member;
import hello.core.section1_6.order.entity.Order;

public interface OrderService {

    Order makeOrder(Order order , Member member);
}
