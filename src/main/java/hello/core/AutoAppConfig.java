package hello.core;

import hello.core.member.repository.MemberRepository;
import hello.core.member.repository.impl.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
//        basePackages = "hello.core.member",
//        basePackageClasses = AutoAppConfig.class,
        // 시작위치를 지정하지 않으면 AutoAppConfig 클래스가 있는 패키지부터 시작해서 찾아나간다
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    // The bean 'memoryMemberRepository', defined in class path resource [hello/core/AutoAppConfig.class], could not be registered. A bean with that name has already been defined in file [D:\springboot\core\out\production\classes\hello\core\member\repository\impl\MemoryMemberRepository.class] and overriding is disabled.
    // 수동 등록빈이 우선권을 가진다
    // 수동 빈이 자동빈을 오버라이딩 해준다
//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}
