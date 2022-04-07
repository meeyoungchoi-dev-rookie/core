package hello.core.section1_6.order.repository;

import hello.core.section1_6.order.entity.Order;

public interface OrderRepository {

    Order makeOrder(Order order);
}
