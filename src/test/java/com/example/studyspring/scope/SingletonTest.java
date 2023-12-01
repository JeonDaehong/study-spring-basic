package com.example.studyspring.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 아래 처럼 new AnnotationConfigApplicationContext(SingletonBean.class); 해주면
 * @Component 없이도 자동으로 SingletonBean 라는 클래스가 @Component에 등록 된 것 같은 효과가 된다.
 */
public class SingletonTest {

    /**
     * 기본은 싱글턴이기 때문에 아래처럼 2개의 빈을 호출해도 두 빈은 같은 주소를 갖는다.
     * 그리고 @PostConstruct 와 @PreDestroy 는 한 번만 호출 된다.
     */
    @Test
    void singletonBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
        SingletonBean bean1 = ac.getBean(SingletonBean.class);
        SingletonBean bean2 = ac.getBean(SingletonBean.class);
        System.out.println("bean1 = " + bean1);
        System.out.println("bean2 = " + bean2);
        assertThat(bean1).isSameAs(bean2);

        ac.close();
    }

    @Scope("singleton") // 싱글턴은 Default 라서 굳이 이렇게 안해줘도 됨.
    static class SingletonBean {

        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }

    }

}
