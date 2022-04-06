package hello.core.section1_4.domain.entity;

public class Discount {
    private Integer discountForVip;

    public Discount(Integer discountForVip) {
        this.discountForVip = discountForVip;
    }

    public Integer getDiscountForVip() {
        return discountForVip;
    }
}
