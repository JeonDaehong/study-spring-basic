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

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
