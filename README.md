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

--------------------------------------------------------------------------------
# 주문과 할인 정책

- 회원은 상품을 주문할 수 있다
- 회원은 등급에 따라 할인 정책을 적용할 수 있다
- 할인 정책인 모든 VIP는 1000원을 할인 해주는 고급 금액 할인을 적용
- 할인 정책은 변경가능성이 높다 (아직 기본 할인 정책을 정하지 못했다는 가정 )

```java
@Override
public void makeOrder(Order order , Discount discountForVip) {
	Member member = order.getMember();
	boolean status = member.checkMemberLevel();
	if (status) {
	    discountPolicy.discount(order, discountForVip);
	}
}
```

## 문제점
- Order 객체가 Member 객체에 의존하고 있다
- Member 객체를 변경하는 경우 Order 객체에 영향을 줄 수 있다

## 수정후
- Order 객체는 주문과 관련된 데이터만 관리한다
- Member 객체는 회원과 관련된 데이터만 관리한다
- OrderService에서  회원이 VIP등급인 경우 Discount 구현체의 discount 메서드를 통해 금액을 할인해준다
- Discount 인터페이스는 고정할인 정책과 비율할인 정책으로 나눠진다
- 두 구현체 모두 금액 할인 이라는 공통 책임을 갖는다
- 따라서 DiscountPolicy 인터페이스로 공통 역할을 추상화 하였다
- 추상화를 함으로써 새로운 할인정책이 추가되더라도 구현체가iscountPolicy 인터페이스 타입이라면 공통 역할을 수행할 수 있게 된다

```java
public class OrderServiceImpl implements OrderService {

    private MemberRepository memberRepository = new MemoryMemberRepository();

    private DiscountPolicy discountPolicy = new FixDiscountPolicy();

    private static final Integer discountVipPrice = 1000;

    @Override
    public Order makeOrder(Member member, Order order) {
        Member result = memberRepository.findBySequence(member.getMemberSequence());

        Integer discountPrice = 0;
        if (result.checkMemberLevel()) {
            discountPrice = discountPolicy.discount(order , discountVipPrice);
        }

        return new Order(order.getProductName() , order.getProductPrice() , discountPrice);
    }
}
```

```java
public interface DiscountPolicy {
    Integer discount(Order order, Integer discountPrice);
}
```

```java
public class FixDiscountPolicy implements DiscountPolicy {

    private MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public Integer discount(Order order, Integer discountPrice) {
        return order.getOrderTotalPrice() - discountPrice;
    }
}
```

## 수정해야 하는 부분
- Discount 객체에서는 회원 등급에 따라 얼마를 할인해줄지 할인 금액만 반환해 준다
- 실제 할인 이후 금액은 Order에서 계산한다

# IoC
- 제어의 역전
- OrderServiceImpl 객체가 직접 인터페이스에 의존하는 객체를 생성하고 주입하여 관리했다
- AppConfig 클래스가 인터페이스에 의존하는 구현객체를 직접 생성해주고 의존관계를 주입해준다
- 즉 , 제어권이 구현객체에서 AppConfig로 넘어갔다
- 스프링 프레임워크가 제워권을 갖게 된다
- 클래스가 스스로 구현객체를 생성하고 주입하지 않는다

# DI
- AppConfig 클래스가 인터페이스에 의존하는 구현객체를 직접 생성한다
- 해당 구현체가 의존하고 있는 객체에 직접 의존관계를 주입해 준다
- 의존 객체가 생성되고 객체의 참조값이 인터페이스 타입에 연결된다

# IoC 컨테이너 DI 컨테이너
- IoC 컨테이너 또는 DI 컨테이너 라고 한다
- 컨테이너가 객체를 직접 생성및 관리하고 의존관계를 주입해 준다
- 의존관계 주입에 초점을 맞춰 최근에는 DI 컨테이너 라고 한다
- 여러 DI 컨테이너 오픈소스가 많이 있다
- 어샘블로 또는 오브젝트 팩토리 라고 부리기도 한다