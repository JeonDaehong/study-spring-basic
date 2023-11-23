package com.example.studyspring.order;

import com.example.studyspring.AppConfig;
import com.example.studyspring.member.Grade;
import com.example.studyspring.member.Member;
import com.example.studyspring.member.MemberService;
import com.example.studyspring.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {

        // given
        Long memberId = 1L;
        Member member = new Member(memberId, "홍길동", Grade.VIP);

        // when
        memberService.join(member);
        Order order = orderService.createOrder(memberId, "아이템A", 10000);

        // then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}
