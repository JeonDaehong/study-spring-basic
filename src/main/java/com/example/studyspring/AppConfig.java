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

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
