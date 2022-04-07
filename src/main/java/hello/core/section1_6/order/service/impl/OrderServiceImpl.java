package hello.core.section1_6.order.service.impl;

import hello.core.section1_6.discount.DiscountPolicy;
import hello.core.section1_6.discount.impl.FixDiscountPolicy;
import hello.core.section1_6.member.entity.Member;
import hello.core.section1_6.order.entity.Order;
import hello.core.section1_6.order.infra.OrderRepositoryImpl;
import hello.core.section1_6.order.repository.OrderRepository;
import hello.core.section1_6.order.service.OrderService;

public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository = new OrderRepositoryImpl();
    private DiscountPolicy discountPolicy  = new FixDiscountPolicy();

    public Order makeOrder(Order order , Member member) {
        int price = order.getPrice() * order.getItemCount();
        int discountPrice = discountPolicy.discount(member , price);
        return orderRepository.makeOrder(new Order(order.getItemName() , order.getItemCount() , order.getPrice() , order.getTotalPrice() , discountPrice));
    }
}
