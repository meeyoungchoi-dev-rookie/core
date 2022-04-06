package hello.core.section1_3.domain.discount.entity;

public class Order {
    private Long sequence = 0L;
    private String userId;
    private String productName;
    private Integer productPrice;

    public Order(Long sequence, String userId, String productName, Integer productPrice) {
        this.sequence = sequence;
        this.userId = userId;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "sequence=" + sequence +
                ", userId='" + userId + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}
