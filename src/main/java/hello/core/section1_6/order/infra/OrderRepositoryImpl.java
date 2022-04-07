package hello.core.section1_6.order.infra;

import hello.core.section1_6.order.entity.Order;
import hello.core.section1_6.order.repository.OrderRepository;

public class OrderRepositoryImpl implements OrderRepository {

    @Override
    public Order makeOrder(Order order) {
      int result = order.calculatePrice();
      return new Order(order.getItemName() , order.getItemCount(), order.getPrice() , order.getTotalPrice() , result);
    }
}
