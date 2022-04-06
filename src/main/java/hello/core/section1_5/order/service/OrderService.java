package hello.core.section1_5.order.service;

import hello.core.section1_5.member.Member;
import hello.core.section1_5.order.Order;

public interface OrderService {

    Order makeOrder(Member member , Order order);
}
