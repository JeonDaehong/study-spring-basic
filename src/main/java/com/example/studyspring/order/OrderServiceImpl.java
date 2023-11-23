package com.example.studyspring.order;

import com.example.studyspring.discount.DiscountPolicy;
import com.example.studyspring.discount.FixDiscountPolicy;
import com.example.studyspring.discount.RateDiscountPolicy;
import com.example.studyspring.member.Member;
import com.example.studyspring.member.MemberRepository;
import com.example.studyspring.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
