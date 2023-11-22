package com.example.studyspring.discount;

import com.example.studyspring.member.Member;

public interface DiscountPolicy {

    /**
     * @return 할인 대상 금액
     */
    int discount(Member member, int price);

}
