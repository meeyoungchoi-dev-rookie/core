package hello.core.domain.order;

public class OrderStatus {
    private boolean status;

    public OrderStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }
}
