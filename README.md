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

# 회원 도메인 설계
## 회원 도메인 협력 관계

![회원도메인 협력관계](https://user-images.githubusercontent.com/42866800/161555578-3e6867f4-4900-4521-ba5b-8def12c5106a.png)

## 회원 클래스 다이어그램

- 회원 가입과 조회 시스템을 구성하는 클래스 사이의 관계를 표현
- 인터페이스는 책임이다
- 즉 객체가 해야하는 일이다
- 객체가 외부에 제공하는 인터페이스는 객체가 수행하는 책임이다
- 인터페이스와 구현체 관곌르 can do this 관계라고 한다
- 즉 , MemberService 인터페이스를 구현한 구현체는 회원을 가입시키고 회원을 조회할 수 있다

![회원클래스다이어그램](https://user-images.githubusercontent.com/42866800/161555582-8d476910-1979-4c38-9127-c99db4365ea9.png)

## 회원 객체 다이어그램

- 구체화된 구현체는 제외하고 추상화된 인터페이스를 기반으로 객체의 협력 관계를 파악할 수 있다

![회원객체다이어그램](https://user-images.githubusercontent.com/42866800/161555570-99438a3a-3b00-4185-a798-98e1b6c29268.png)
## 현재 설계의 문제점

- 이 코드의 설계상 문제점은 무엇일까
  - MemberServiceImpl 구현체가 MemberRepository와 MemoryMemberRepository에 의존하고 있다
  - 즉 MemberServierImpl 클래스가 MemoryMemberRepository 클래스를 참조하고 있다
  - 또한 MemoryRepository 인터페이스도 참조하고 있다
- 다른 저장소로 변경할때 oop 원칙을 잘 준수할까
  - DbMemberRepository로 인터페이스 구현체를 변경하는 경우
  - MemberServiceImpl 구현체가 의존하고 있는 구현체를 변경해줘야 한다
  - 즉 , MemberRepository의 구현체만 갈아끼운다고 해결될수 있는 문제가 아니다
- DIP를 지키고 있는가
  - MemberServiceImpl 구현체가 변경가능성이 높은 MemoryMemberRepository 에 의존하고 있다
  - MemberServiceImpl 구현체는 MemberRepository 인터페이스에도 의존하고 있다
  - 인터페이스가 바뀌면 MemberServiceImpl 구현체의 코드도 변경해줘야 한다