## 프로젝스 생성
- start.spring.io 에서 spring web을 주입하지 않으면 프로젝트 생성후 run 했을때 톰켓이 올라가지 않늗다
## 비즈니스 요구사항
- 회원
    - 가입 조회
    - 일반 VIP 등급
    - DB 사용여부 미확정
- 주문과 할인 정책
    - 회원은 상품 주문
    - 회원등급에 따른 할인 정책
    - 모든 vip 1000원 할인 고정 금액할인 정책
    - 할인 정책인 변경 가능성이 높다
- 인터페이스 기반 구현체 생성 방식

회원이 주문을 한다

회원이 상품을 장바구니에 담는다

장바구니에 여러개의 상품이 담길수 있다

장바구니에 담긴 상품을 주문한다

Order는 주문 내역을 처리

Item에는 상품 정보가 있다

Order에 Map을 선언

Map은 상황에 따라 장바구니 역할을 할수도 있고 주문 역할을 할수 도 있다

사용자가 주문을 누르면 주문 내역이 된다

Map에 주문 인지 장바구니 인지 판단하기 위한 변수 필요

사용자가 상품 여러개를 장바구니에 담는것 어떻게 처리

누구의 장바구니 인지

무슨 상품을 담았는지

주문이 됬는지 아니면 장바구니 상태인지

```java
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
```

- 문제점
- 한 회원이 상품을 장바구니에 담을때 마다 Order 객체가 새롭게 만들어진다
- orderNo 번호가 달라진다
- 회원이 상품 여러개를 장바구니에 담을 수 없게 된다
- 해결책
- Map의 value를 Order 객체가 아닌 Order 타입의 List로 저장한다
- Map의 key는 List 객체가 최조로 생성될때 딱 한번 만들어진다
- 그러면 장바구니 시퀀스 하나에 List 객체가 있고 List에 여러개의 Order가 담길수 있지않을까

```java
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
```

```java
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
```

```java
@Override
public Long createSequence() {
    return MemoryOrderRepositoryVer2.orderNo += 1;
}
```