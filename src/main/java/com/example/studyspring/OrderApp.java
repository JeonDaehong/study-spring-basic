package com.example.studyspring;

import com.example.studyspring.member.Grade;
import com.example.studyspring.member.Member;
import com.example.studyspring.member.MemberService;
import com.example.studyspring.member.MemberServiceImpl;
import com.example.studyspring.order.Order;
import com.example.studyspring.order.OrderService;
import com.example.studyspring.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();

        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memberId = 1L;
        Member member = new Member(memberId, "홍길동", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "아이템A", 20000);

        System.out.println("order = " + order.toString());
        System.out.println("order.calculatePrice = " + order.calculatePrice());

    }
}
