package com.example.studyspring.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService bean1 = ac.getBean(StatefulService.class);
        StatefulService bean2 = ac.getBean(StatefulService.class);

        //ThreadA: A사용자 10000원 주문
        bean1.order("userA", 10000);

        //ThreadB: B사용자 20000원 주문
        bean1.order("userB", 20000);

        //ThreadA: 사용자A 주문 금액 조회
        int price = bean1.getPrice();
        System.out.println("price = " + price);

        // userA니까 10000원이 나오기를 기대헀지만, 싱글턴이므로 20000원이 나온다.
        // 그래서 싱글턴은 무상태로 설계하고, 공유 필드를 사용하지 말아야 한다.
        assertThat(bean1.getPrice()).isEqualTo(20000);

    }

    @Configuration
    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}
