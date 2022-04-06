package hello.core.section1_4.domain.repository;


import hello.core.section1_4.domain.entity.Order;

public interface OrderRepository {

    // 주문
    Long order(Order order , int discountPrice);
}
