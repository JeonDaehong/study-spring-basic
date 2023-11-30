package com.example.studyspring.autowired;

import com.example.studyspring.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean {

        // 스프링 빈에서 의존관계 주입할 것이 없을 때
        // required 가 true면 오류가 발생하고
        // required 가 false면 해당 메서드를 호출조차 하지 않고 그냥 넘어간다.
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        // 스프링 빈에서 의존관계 주입할 것이 없을 때 Null이 들어간다.
        @Autowired
        public void setNoBean2(@Nullable  Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        // 스프링 빈에서 의존관계 주입할 것이 없을 때 Optional.empty 가 들어간다.
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }

    }

}
