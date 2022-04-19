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

## 스프링 컨테이너
- ApplicationContext가 스프링 컨테이너이다
- 인터페이스를 구현한 AnnotationConfigApplication 구현체를 통해 AppConfig 설정 정보를 사용하여 스프링 컨테이너에 빈을 등록하고 의존관계를 주입한다

### 스프링 빈 의존관계 설정
- 스프링 컨테이너는 AppConfig 클래스의 @Bean 어노테이션이 붙은 메서드를 참고하여 의존관계를 주입해 준다
- 빈을 생성하는 단계와 의존관계를 주입하는 단계가 나뉘어져 있다

### 모든 빈 출력하기
- 스프링에서 제공하는 모든 빈 정보를 알고싶을때 사용
- 스프링 컨테이너에 등록된 모든 빈 이름 정보를 가져와서 빈복문을 돌린다
- bean 이름을 사용하여 bean을 꺼낸다


### 애플리케이션 빈 출력하기
- 스프링이 제공하는 빈 말고 애플리케이션을 사용하기 위해 등록한 빈 정보만 출력하고 싶을때 사용
- BeanDifinication의 타입을 ROLE_APPLICATION으로 설정해 줘야 한다

### 스프링 빈 조회 기본

- 빈 이름으로 조회

```java
@Test
@DisplayName("빈 이름으로 조회")
void findBeanByName() {
    MemberService memberService = ac.getBean("memberService", MemberService.class);
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

}
```

- 빈 타입으로 조회

```java
@Test
@DisplayName("이름 없이 타입으로 조회")
void findBeanByType() {
    MemberService memberService = ac.getBean(MemberService.class);
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

}
```

- 구체 타입으로 조회
- 스프링 컨테이너에 등록된 인스턴스 타입을 보고 빈이 결정된다
- 하지만 구현타입으로 조회하는 것은 좋치 않다
- DIP 원칙에 따라 역할에 의존하는 것이 좋기 떄문이다

```java
@Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByType2() {
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        

    }
```

- 빈 조회시 예외가 발생하는지 테스트
- 스프링 컨테이너에 등록되지 않은 빈을 조회할때 NoSuchBeanDefinitionException 이 발생하는지 테스트

```java
@Test
@DisplayName("빈 이름으로 조회x 예외가 던져지는지 확인하기 위한 테스트")
void findBeanByNamex() {
    // org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'xxxx' available
    assertThrows(NoSuchBeanDefinitionException.class,
            () -> ac.getBean("xxxx", MemberService.class));
}
```

### xml로 빈 설정
- appConfig.xml 파일을 사용하여 빈설정 테스트
- xml 파일을 읽어오기 위해 GenericXmlApplicationContext 객체를 사용한다

```java
public class XmlAppContext {

    @Test
    void xmlAppContext() {
       ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
       MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
```

## BeanDefinition - 빈 설정 메타정보

- 스프링은 어떤게 AppConfig 기반 클래스 설정정보와 xml 기반 설정정보를 지원할 수 있는 것 인가
- 역활과 구조를 개념적으로 나눠놨다
- 즉  인터페이스를 통해 추상화 시켰다
- BeanDifinition 인터페이스를 구현한 애들이 AppConfig.class 와 appConfig.xml 이다
- 따라서 BeanDifinition 인터페이스는 구현체가 무엇인지 신경쓰지 않아도 된다
- 구현체가 구현한 타입이 BeanDifinition 이기 만 하면 빈을 스프링 컨테이너에 등록할 수 있다
-

![BeanDefiniton.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/6fcf1c7f-d2e2-455e-8b35-5e498873a930/BeanDefiniton.png)

### BeanDefinition 정보
- BeanClassName: 생성할 빈의 클래스 명(자바 설정 처럼 팩토리 역할의 빈을 사용하면 없음)
- factoryBeanName: 팩토리 역할의 빈을 사용할 경우 이름, 예) appConfig
- factoryMethodName: 빈을 생성할 팩토리 메서드 지정, 예) memberService
- Scope: 싱글톤(기본값)
- lazyInit: 스프링 컨테이너를 생성할 때 빈을 생성하는 것이 아니라, 실제 빈을 사용할 때 까지 최대한
- 생성을 지연처리 하는지 여부
- InitMethodName: 빈을 생성하고, 의존관계를 적용한 뒤에 호출되는 초기화 메서드 명
- DestroyMethodName: 빈의 생명주기가 끝나서 제거하기 직전에 호출되는 메서드 명
- Constructor arguments, Properties: 의존관계 주입에서 사용한다


## 싱글톤이 없다면
- 클라이언트가 요청을 보낼때 마다 AppConfig가 memberServie 객체를 새롭게 생성하게 된다
- memberService에 의존하고 있는 MemoryMemberRepository 객체도 새롭게 생성된다
- 아래의 테스트 예제에서 memberService 객체를 두번생성하는데
- 총 4개의 객체가 만들어 지게 된다
- 만약 5000만개 혹은 그 이상의 객체가 계속 만들어지면 메모리 낭비가 심해지게 된다

```java
public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        MemberService memberService1 = appConfig.memberService();

        MemberService memberService2 = appConfig.memberService();

        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }
}
```

# 싱글톤 패턴을 적용하는 방법

- 무조건 한개만 생성되어야 하는 객체 인스턴스에 static final을 붙인다
- 외부에서 생성자를 통해 객체가 생성되는 것을 막기 위해 생성자의 접근제한자를 private으로 설정한다
- 외부에서 static 영역에 만들어진 객체 인스턴스의 주소를 공유하기 위해 static 메서드를 만들고 객체 인스턴스의 주소를 반환해 준다

## 싱글톤 컨테이너
- 스프링 컨테이너가 싱글톤 방식으로 만들어져 있다
- 따라서 직접 싱글톤 객체를 생성하지 않아도 된다


# 싱글톤 방식의 문제점
## 무엇

- 객체 인스턴스를 공유하기 때문에 필드를 공유 필드로 선언하게 되면 클라이언트가 기대한 값과 다른 값이 나오게되는 문제가 생길 수 있다

### 문제점

- StatefulService 라는 클래스 타입을 선언한다
- 해당 객체를 싱글톤으로 만드는데 price 필드는 공유한다
- 주문하는 메서드를 선언하고 name과 price를 파라미터로 받는다

```java
public class StatefulService {

    private int price;

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
```

- 싱글톤으로 StatefultService 객체 인스턴스를 생성한다
- 가정
- 사용자A가 10000원을 주문했다
- 동시에 사용자B가 20000원을 주문했다
- 사용자A가 결제를 하기 위해 자신의 금액을 확인하는데 20000원이 출력된다
- 왜?
- StatefulService 객체가 싱글톤이라서 공유되고 price필드가 공유변수로 선언되 있기 때문이다
- 즉 , 사용자A가 주문했을때는 price 필드의 값이 10000원이였다
- 하지만 사용자B가 주문하면 공유되고 있는 객체의 price 필드의 값이 20000으로 바뀐다
- 따라서 A가 자신의 금액을 조회했을때 20000원이 출력되게 된다

## 스프링이 빈을 등록할때 싱글톤으로 등록되는 원리
- 스프링 컨테이너가 빈을 등록하기 전에 스프링 컨테이너에 빈 등록 여부를 확인한다
- 있으면 해당 빈을 스프링 컨테이너에서 찾아 반환한다
- 없으면 스프링 컨테이너에 해당 빈을 등록하고 반환한다

```java
@Bean
public MemberService memberService() {
    
	if (memoryMemberRepository가 이미 스프링 컨테이너에 등록되어 있다면?) {
		return 스프링 컨테이너에서 찾아서 반환;
	} else {
			// 스프링 컨테이너에 등록된 빈이 없다면
      // 기존 로직을 호출해서 MemoryMemberReposittory를 생성하고 스프링 컨테이너에 등록
			return 반환
	}

}
```

## 컴포넌트 스캔과 의존관계 자동 주입
### 무엇

- 기존에 수동으로 빈을 등록해주는 작업을 자동으로 등록되게 바꾼다
- 왜?
- 수동으로 빈을 등록하려면 AppConfig 설정파일도 만들어야 하고
- 빈으로 등록되어야 하는 클래스에 @Bean 어노테이션을 붙여줘야 하기 때문이다

### 어떻게
- @ComponentScan 어노테이션을 AutoAppConfig 설정파일에 붙인다
- 그런데 AutoAppConfig에는 @Bean 어노테이션을 사용하여 등록한 클래스가 없다
- 왜?
- @ComponentScan 어노테이션을 붙이면 자동으로 @Component 어노테이션이 붙은 클래스를 찾아 스프링 컨테이너에 빈으로 등록해 준다
- @Configuration 어노테이션을 설정파일에 붙였을때 @Bean 어노테이션이 붙은 클래스가 자동으로 빈으로 등록된 이유도 @Configuration 어노테이션이 @Component 어노테이션이 붙었기 때문이다

## @Autowired

- 이전에는 @Bean을 통해 스프링 컨테이너에 빈이 등록되면
- 생성자를 통해 직접 의존관계를 주입받았다
- @Autowired 어노테이션을 사용하면 스프링 컨테이너가 자동으로 의존관계를 주입해 준다

## 자동으로 빈이 잘 등록되었는지 테스트

- AutoAppConfig 클래스가 설정정보 클래스이다
- 따라서 AnnotationConfigApplicationContext 구현객체를 사용하여 해당 클래스 파일을 읽도록 설정해 준다
- 그런데 이전과 다르게 AutoAppConfig 클래스에 @Bean 어노테이션이 없다
- 자동으로 스프링 컨테이너에 빈으로 등록될 수 있도록 클래스에 @Component 어노테이션을 붙였기 때문이다
- 스프링 컨테이너에 자동으로 등록된 MemberService 빈을 가져온다
- 스프링 컨테이너에서 가져온 빈 의 타입과 클래스의 타입이 같은지 검사한다

```java
public class AutoAppConfigTest {

    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
```


### 생성자 주입

- 생성자를 통해 의존관계를 주입받는다
- 추상 타입 필드에 final이 붙은 경우 생성자를 통해 주입할 때 해당 타입의 의존객체가 null이면 안된다
- 한번 의존관계가 주입되면 수정할 수 없다
- 따라서 의존관계가 불변이거나 의존관계가 필수 일때 사용한다
- 생성자가 한개만 있는 경우 @Autowired를 생략해도 된다
- 생성자 주입은 클래스의 빈을 등록하면서 의존관계 주입도 같이 일어난다

```java
@Component
public class OrderServiceImpl implements OrderService {

    
     private final MemberRepository memberRepository;
     private final DiscountPolicy discountPolicy;
    
		@Autowired
		public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
		  System.out.println("1. OrderServiceImpl.OrderServiceImpl");
		  this.memberRepository = memberRepository;
		  this.discountPolicy = discountPolicy;
		}

    ...

}
```

## 다양한 의존관계 주입 방법
### 수정자 주입

- set 메서드를 통해 의존관계를 주입한다
- 주입할 의존관계 대상이 없는경우 에러가 발생한다
- 따라서 의존관계를 주입하지 않아도 되는 경우 required = false 설정을 해줘야 한다
- 선택적 의존관계 또는 변경 가능성이 있는 의존관계를 주입할때 사용한다

```java
@Component
public class OrderServiceImpl implements OrderService {

    private final   MemberRepository memberRepository;
    private final   DiscountPolicy discountPolicy;

    @Autowired(required = false)
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {

        this.discountPolicy = discountPolicy;
    }

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    ...

}
```

- 생성자 주입과 수정자 주입이 같이 있는 경우 생성자 주입이 먼저 된다

### 필드 주입

- 추상타입 필드에 의존관계를 직접 주입한다
- 테스트시 의존관계를 설정하기 힘들다는 단점이 있다
- 왜?
- 테스트 코드는 자바코드이므로 따로 set 메서드 또는 생성자를 만들어 의존관계를 주입해 줘야 한다
- 권장되지 않는 방법이다

### 일반 메서드 주입

- 일반 메서드에 @Autowired 어노테이션을 붙이고 파라미터로 주입할 의존객체를 넣어준다
- 파라미터를 여러개 넣을 수 있다는 장점이 있다

# 옵션 처리
## 무엇

- 의존관계 주입한 빈이 없어도 정상적으로 동작시키기 위해 사용

## 어떻게

- 테스트 진행
- TestBean이라는 클래스를 생성
- @Autowired 어노테이션의 required 속성을 false로 지정
- 의존관계가 없는 경우 메서드가 실행되지 않는다
- Member 객체에 @Component 어노테이션이 붙어있지 않다
- 따라서 TestBean 클래스와 의존관계가 없기 때문에 setNoBean1 메서드 자체가 아얘 실행되지 않는다

```java
@Autowired(required = false)
public void setNoBean1(Member noBean1) {
    System.out.println("noBean1 = " + noBean1);
}
```

- 의존관계 주입 해주려는 Member 빈에 @Nullable 어노테이션이 붙어 있다
- 즉 , 빈이 null 이여도 에러를 뱉지 않는다
- 그냥 null이 출력된다

```java
@Autowired
public void setNoBean2(@Nullable Member noBean2) {
    System.out.println("noBean2 = " + noBean2);
}
```

- 자바 1.8 Optional 객체를 사용하여 의존관계 주입하려는 빈을 null 체크 한다
- Member 빈이 null인 경우 Optional.empty를 반환해준다

```java
@Autowired
public void setNoBean3(Optional<Member> noBean3) {
    System.out.println("noBean3 = " + noBean3);
}
```


## 생성자 주입을 선택해야 하는 이유

- 필드가 누락되는 것을 방지할 수 있다
- 테스트시 의존관계 주입이 편하다

```java
public class OrderServiceImplTest {

    @Test
    void creatOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.register(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
```


# 동일한 타입의 빈이 두개 이상 스프링 컨테이너에 등록되 있을때 의존관계 주입시 충돌을 해결하는 방법
## 무엇

- 이전까지 DiscountPolicy 구현객체를 하나만 빈으로 등록했었다
- 이제 FixDiscountPolicy 구현객체까지 빈으로 등록하고 테스트

```java
@Component
public class FixDiscountPolicy implements DiscountPolicy{

	...
}
```

## 발생하는 에러

- NoUniqueBeanDefinitionException
- 에러가 발생하는 이유
- 빈을 등록할 때 동일한 타입의 빈을 2개 이상 등록하려고 했기 때문에 발생하는 문제이다
- RateDiscountPolicy 구현객체에 @Component 어노테이션을 붙여 빈으로 등록했다
- 그런데 동일한 DiscountPolicy 타입의 FixDiscountPolicy 구현객체에 @Component 어노테이션을 붙여 빈으로 등록하려고 하니
- 스프링 입장에서는 어떤 구현객체를 빈으로 등록해야 하는지 모르게 된다

## 해결방법

### @Autowired 필드명

- 동일한 타입의 빈이 두개가 등록된 경우 의존관계주입을 위해
- 타입뒤에 의존관계주입할 구현객체 타입을 소문자로 적어준다

```java
@Autowired
private DiscountPolicy rateDiscountPolicy;
```

- 생성자 의존관계 주입을 사용하는 경우

```java
@Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = rateDiscountPolicy;
    }
```

### @Qualifier

- 동일한 타입을 구현한 구현객체에 @Qualifier(”필드명우선순위”)를 지정해 준다

```java
@Component
@Qualifier("mainDiscountPolicy")
public class RateDiscountPolicy implements DiscountPolicy{
		...

}
```

```java
@Component
@Qualifier("secondDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy{
		...

}
```

```java
@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

		...
}
```

- @Component 로 빈을 등록할때 말고 수동으로 @Bean 어노테이션을 사용하여 빈을 등록할때도 @Qualifier 어노테이션을 사용할 수 있다

### @Primary

- 빈등록시 우선순위를 지정하는 어노테이션
- RateDiscountPolicy 구현객체 빈이 우선적으로 의존관계 주입이 될 수 있게 설정하겠다

```java
@Component
@Primary
public class RateDiscountPolicy implements DiscountPolicy{

    ...
}
```

- 메인 데이타베이스 커넥션을 가져오는 경우 @Primary 어노테이션을 붙여 무조건 메인 데이터베이스 커넥션이 주입될 수 있게 처리


## 언제 빈 자동 등록과 수동 등록을 사용해야 하는가
## 언제 자동 등록을 사용해야 하는가
- 비즈니스 로직에서 다형성이 필요한 경우
- 하나의 인터페이스 타입에 여러개의 구현객체가 선택될수 있는 경우
- 객체의 역할이 상황에 따라 변할 수 있는 경우
- 단 , 수동등록도 고려해볼 필요성이 있다
- 왜?
- 다형성의 경우 이를 개발한 사람은 이해하기 쉽지만 다른 사람이 코드를 볼때는 관계있는 모든 패키지를 확인해야 하는 번거로움이 있다

## 언제 수동 등록을 사용해야 하는가
- 기술지원 객체를 사용하는 경우
- 예) db 관련 리포지터리


# 빈 스코프
- 빈이 존재할 수 있는 범위

### 싱글톤 스코프

- 스프링 컨테이너의 시작과 종료까지 유지되는 가장 넓은 범위의 스코프
  ![싱글톤 빈](https://user-images.githubusercontent.com/42866800/163944187-5a079fc0-476e-4d13-9aaf-5d93824be612.png)
  
- 싱글톤 스코프의 빈을 스프링 컨테이너에 요청한다
- 스프링 컨테이너는 본인이 관리하는 스프링 빈을 반환한다
- 스프링 컨테이너에 같은 요청이 와도 같은 객체 인스턴스인 스코프 빈을 반환한다

### 프로토타입

- 스프링 컨테이너가 빈의 생성과 의존관계 주입까지만 관여하고 나머지는 관리자하지 않는 스코프

### 빈 관련 스코프

- `request` - 웹 요청이 들어오고 나갈때 까지 유지되는 스코프
- `session` - 웹 세션이 생성되고 종료될 때까지 유지되는 스코프
- `application` - 웹의 서블릿 컨텍스트와 같은 범위로 유지되는 스코프

## 프로토타입 스코프
![프로토타입 빈](https://user-images.githubusercontent.com/42866800/163944233-8c5115b0-f185-472f-bdf0-e027fd627de5.png)
- 스프링 컨테이너는 생성한 프로토타입 빈을 클라이언트에게 반환한다
- 이후에 스프링 컨테이너에 같은 요청이 오면 항상 새로운 프로토타입 빈을 생성해서 반환한다
- 스프링 컨테이너는 프로토타입 빈을 생성하고 의존관계 주입 및 초기화 까지만 처리한다
- 클라이언트에게 빈을 반화한후 더이상 스프링 컨테이너는 생성된 프로타입 빈을 관리하지 않는다
- 프로토타입 빈을 관리할 책임은 프로토타입 빈을 받은 클라이언트에게 있다
- 따라서 빈 종료 메서드가 호출되지 않는다


## Provider로 문제를 해결하는 방법
- Dependency Lockup(DL) - 의존관계 조회 , 탐색
- 의존관계를 외부에서 주입 받는게 아니라 직접 필요한 의존관계를 찾는것
- 애플리케이션 컨텍스트 전체를 주입받게 되면 스프링 컨테이너에 종속적인 코드가 된다
- 또한 단위 테스트를 작성하기 어려워 진다
- 지정한 프로토타입 빈을 컨테이너에서 대신 찾아주는 DL 기능만 있으면 된다