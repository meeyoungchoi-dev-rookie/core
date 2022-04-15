package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();

        // org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'BeanA' available
        // 왜?
        // BeanB에는 MyExcludeComponent 어노테이션이 붙어 있다 즉 , 빈 등록대상이 아닌데 빈을 가져오려고 하니 에러가 발생한다
        // BeanB beanB = ac.getBean("BeanB", BeanB.class);
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanB", BeanB.class));
         // BeanB에 MyExcludeComponent 어노테이션이 적용되어 있어 빈으로 등록되지 않는다
    }

    @Configuration
    @ComponentScan(
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    ) // 어노테이션과 관련된 필터를 만들겠다
    static  class ComponentFilterAppConfig {

    }


}
