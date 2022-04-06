package hello.core.section1_5.order;

public class Order {
    private String productName;
    private int productPrice;
    private int orderTotalPrice; // 할인 적용된 최종 금액

    public Order(String productName, int productPrice, int orderTotalPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.orderTotalPrice = orderTotalPrice;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public int getOrderTotalPrice() {
        return orderTotalPrice;
    }
}
