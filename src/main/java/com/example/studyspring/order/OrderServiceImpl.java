package com.example.studyspring.order;

import com.example.studyspring.discount.DiscountPolicy;
import com.example.studyspring.discount.FixDiscountPolicy;
import com.example.studyspring.member.Member;
import com.example.studyspring.member.MemberRepository;
import com.example.studyspring.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
