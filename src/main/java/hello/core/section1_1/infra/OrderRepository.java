package hello.core.section1_1.infra;

import hello.core.section1_1.domain.order.OrderStatus;
import hello.core.section1_1.domain.product.Item;


public interface OrderRepository {

    // 장바구니 상태
    public void insetIntoOrders(String userId, Item item);

    // 주문 상태
    public Long updateOrders(Long orderNo , OrderStatus orderStatus);

    // 장바구니 시퀀스 증가
    public Long createSequence();

}
