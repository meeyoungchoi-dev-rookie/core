package hello.core.scan.filter;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

//해당 어노테이션이 붙은 클래스를 컴포넌트 스캔 대상에 추가하겠다
@Target(ElementType.TYPE)// TYPE이면 클래스 레벨에 붙는다
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
}
