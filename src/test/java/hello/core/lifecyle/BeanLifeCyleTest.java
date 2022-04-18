package hello.core.lifecyle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCyleTest {

    @Test
    public void  lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetwrokClient client = ac.getBean(NetwrokClient.class);
        ac.close();
    }

    @Configuration
    static  class LifeCycleConfig {

        @Bean
        public NetwrokClient netwrokClient() {
            NetwrokClient netwrokClient = new NetwrokClient();
            netwrokClient.setUrl("http://hello.spring.dev");
            return netwrokClient;
        }
    }
}
