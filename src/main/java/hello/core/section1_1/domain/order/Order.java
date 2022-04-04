package hello.core.section1_1.domain.order;

import hello.core.section1_1.domain.product.Item;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private Long orderNo = 0L;
    private Item item;
    private Integer totalPrice;
    private String userId;
    private OrderStatus orderStatus;

    private Map<Long , Order> orders = new HashMap<>();

    public Order(Long orderNo, Item item, Integer totalPrice, String userId , OrderStatus status) {
        this.orderNo = orderNo;
        this.item = item;
        this.totalPrice = totalPrice;
        this.userId = userId;
        this.orderStatus = status;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public Item getItem() {
        return item;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public String getUserId() {
        return userId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNo=" + orderNo +
                ", item=" + item +
                ", totalPrice=" + totalPrice +
                ", userId='" + userId + '\'' +
                '}';
    }
}
