package hello.core.repository;

import hello.core.domain.order.Order;
import hello.core.domain.order.OrderStatus;
import hello.core.domain.product.Item;
import hello.core.infra.OrderRepository;

import java.util.HashMap;
import java.util.Map;

public class MemoryOrderRepository  implements OrderRepository {

    // 주문 내역을 저장하는 Map
    /*
     Long : 주문시 자동 생성 시퀀스
     Order : 주문정보

    * */
    public static Map<Long , Order> orders = new HashMap<>();
    public static Long orderNo = 0L;

    @Override
    public void insetIntoOrders(String userId, Item item) {
        Order order = null;

        if (order.equals(null)) {
            // 장바구니 번호 생성
            MemoryOrderRepository.orderNo += 1;
        }

        // 장바구니에 담긴 총 금액 계산
        Integer totalPrice = order.getTotalPrice();
        totalPrice += item.getPrice() * item.getCount();

        // 장바구니 상태인지 주문 상태인지
        OrderStatus orderStatus = new OrderStatus(false);

        order = new Order(orderNo , item , totalPrice , userId , orderStatus);
    }

    @Override
    public Long updateOrders(Long orderNo, OrderStatus orderStatus) {

        OrderStatus updateOrderStatus = new OrderStatus(true);

        Order order  = orders.get(orderNo);
        OrderStatus before = order.getOrderStatus();
        before = updateOrderStatus;

        orders.put(orderNo, order);

        return orderNo;
    }

    @Override
    public Long createSequence() {
        return null;
    }
}
