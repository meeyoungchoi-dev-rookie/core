package hello.core.section1_4.domain.service;

import hello.core.section1_4.domain.entity.Discount;
import hello.core.section1_4.domain.entity.Order;

public interface OrderService {
    void makeOrder(Order order, Discount discountForVip);
}
