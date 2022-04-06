package hello.core.section1_4.domain.entity;

public class Order {
    private String productName;
    private int productPrice;
    private int productCount;
    private  Member member;

    public Order(String productName, int productPrice, int productCount, Member member) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCount = productCount;
        this.member = member;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public int getProductCount() {
        return productCount;
    }

    public Member getMember() {
        return member;
    }
}
