package hello.core.repository;

import hello.core.domain.order.Order;
import hello.core.domain.order.OrderStatus;
import hello.core.domain.product.Item;
import hello.core.infra.OrderRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryOrderRepositoryVer2 implements OrderRepository {

    public static Map<Long , List<Order>> orders = new HashMap<>();
    public static Long orderNo = 0L;

    @Override
    public void insetIntoOrders(String userId, Item item) {
        Order order = null;
        List<Order> orderList = null;

        Long sequence = 0L;
        if (orderList.equals(null)) {
            orderList = new ArrayList<>();
            sequence = createSequence();
        }

        // 장바구니에 담긴 총 금액 계산
        Integer totalPrice = order.getTotalPrice();
        totalPrice += item.getPrice() * item.getCount();

        // 장바구니 상태인지 주문 상태인지
        OrderStatus orderStatus = new OrderStatus(false);

        order = new Order(orderNo , item , totalPrice , userId , orderStatus);

        orderList.add(order);
        orders.put(sequence , orderList);

    }

    @Override
    public Long updateOrders(Long orderNo, OrderStatus orderStatus) {
        List<Order> orderList = MemoryOrderRepositoryVer2.orders.get(orderNo);

        for (Order order : orderList) {
            OrderStatus before = order.getOrderStatus();
            before = new OrderStatus(true);
        }
        MemoryOrderRepositoryVer2.orders.put(orderNo, orderList);

        return orderNo;
    }

    @Override
    public Long createSequence() {
        return MemoryOrderRepositoryVer2.orderNo += 1;
    }
}
