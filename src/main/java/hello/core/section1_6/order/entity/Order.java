package hello.core.section1_6.order.entity;

public class Order {
    private String itemName;
    private Integer itemCount;
    private Integer price;
    private Integer totalPrice;
    private Integer discountPrice;

    public Order(String itemName, Integer itemCount, Integer price, Integer totalPrice, Integer discountPrice) {
        this.itemName = itemName;
        this.itemCount = itemCount;
        this.price = price;
        this.totalPrice = totalPrice;
        this.discountPrice = discountPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public Integer getDiscountPrice() {
        return discountPrice;
    }

    // 할인 된 금액 계산은 Order 도메인에서 담당한다
    public int calculatePrice() {
        return (this.getItemCount() * this.getPrice()) - this.getDiscountPrice();
    }

    @Override
    public String toString() {
        return "Order{" +
                "itemName='" + itemName + '\'' +
                ", itemCount=" + itemCount +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
