package com.example.studyspring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * @Bean을 사용하고 싶지 않으면, @ComponentScan 과 @Configuration 그리고 @Component,
 * 의존관계를 자동으로 주입하는 @Autowired 를 활용하는 것이 좋다.
 * ( @Autowired 는 스프링 컨테이너가 자동으로 해당 스프링 빈을 찾아서 의존관계를 주입해준다. )
 *
 * @ComponentScan 은 모든 클래스를 뒤지며, @Component 라는 어노테이션이 붙은 모든 클래스 안에 있는 정보들을
 * 자동으로 스프링 컨테이너에 등록해준다. ( @Bean 을 사용해서 수동으로 올려줬던 다른 클래스가 있다면, excludeFilters 를 사용해서 제외시켜야 한다. )
 */
@ComponentScan(
        // 이전에 만들어둔 AppConfig 클래스는 수동으로 Bean 을 끌어올려주는데, 그것까지 여기서 포함시키면 안되기 때문에
        // 이러한 설정 정보를 넣어주는 것이다.
        // ( @Configuration 소스코드를 열어보면 @Component 어노테이션이 붙어있기 때문이다. )
        // 아래 설정 정보 : 어노테이션으로 등록된 Configuration 가 붙은 class 들은 제외시킨다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class),

        // 이렇게 어느 부분만 탐색하라고 지정해줄 수 있다. 패키지 경로를 적을 수도 있고, 아래 처럼 클래스로 지정할 수도 있다.
        // 지정을 하지 않으면, 해당 ComponentScan이 선언된 클래스의 패키지 아래는 전부 다 확인한다.
        // 즉, 이 AutoAppConfig는 package com.example.studyspring; 으로 되어있기 때문에 저 아래 패키지의 @Component는 전부 확인한다고 보면 된다.
        // ***** 가장 권장하는 방법은, 설정 정보 클래스를 프로젝트 최 상단에 두고, 패키지 위치를 지정하지 않는 것이다. *****
        basePackages = {"com.example.studyspring.member", "com.example.studyspring.order"},
        basePackageClasses = AppConfig.class

)
@Configuration
public class AutoAppConfig {



}
