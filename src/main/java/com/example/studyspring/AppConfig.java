package com.example.studyspring;

import com.example.studyspring.discount.DiscountPolicy;
import com.example.studyspring.discount.FixDiscountPolicy;
import com.example.studyspring.discount.RateDiscountPolicy;
import com.example.studyspring.member.MemberRepository;
import com.example.studyspring.member.MemberService;
import com.example.studyspring.member.MemberServiceImpl;
import com.example.studyspring.member.MemoryMemberRepository;
import com.example.studyspring.order.OrderService;
import com.example.studyspring.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Configuration 이 있으면, 그 안에서 @Bean 으로 등록되어 있는 메서드들은
 * 전부 Spring Container에서 관리해준다.
 * ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
 * MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
 * 이거로 Spring에서 관리하는 @Bean으로 등록된 메서드를 사용할 수 있다.
 * @Bean(name="") 이렇게 name을 따로 주지 않는 한 메서드 명이, 이름이 된다.
 *
 * Bean의 이름은 항상 다르게 부여해야 한다.
 */
@Configuration
public class AppConfig {

    // 여기서 보면,
    // @Bean memberService --> new MemoryMemberRepository()
    // @Bean orderService --> new MemoryMemberRepository()
    // 이렇게 new MemoryMemberRepository를 memberService와 orderService에서 각각 한 번씩 생성한다.
    // 이러면 싱글턴 패턴이 깨지게 되는게 아닌가 ???

    // 우리가 처음 예상한건
    // "call AppConfig.memberRepository" 가 3번 출력되는 것이다.
    // 근데 실행시켜보면, 1번만 출력된다.
    // 왜 일까??

    // AppConfig 를 @Configuration 으로 등록했기 때문이다.
    // 그러면 AppConfig 대신 AppConfig 를 복사한 AppConfig CGLIB 라는 싱글턴을 보장하는 클래스로 저장이 된다.
    // 그래서 내부에서는 이미 스프링 컨테이너에 등록되어 1번이상 new 된게 있으면 그 인스턴스 주소로 반환하는 로직이 들어있다.
    // 그래서 MemoryMemberRepository 가 3변 new 된 거 처럼 보이지만, 사실 1번 호출 된 뒤 해당 인스턴스 주소를 사용하여 싱글턴을 유지한 것이다.
    // * 참고로 AppConfig CGLIB 는 AppConfig 의 자식 타입으로 등록이 되므로, AppConfig 타입으로 조회할 수 있었던 것이다.

    // @Configuration 없이 @Bean 만 등록하면 싱글턴이 보장되지 않는다.

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
