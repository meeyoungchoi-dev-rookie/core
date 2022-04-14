package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {
    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        // ThreadA : A 사용자가 10000원 주문
        statefulService1.order("userA", 10000);
        // TheadB : B사용자가 20000원 주문
        statefulService1.order("userB", 20000);

        // Thread: 사용자A 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("priceA = " + price); // 20000
        // 왜 이러한 문제가 생기는가
        // SatefulService 객체 인스턴스가 static이기 때문에 같은 인스턴스를 사용한다
        // 따라서 B가 주문하면 주문 금액이 바뀌게 되어 A한테도 영향을 준다

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

    }

    static class  TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
