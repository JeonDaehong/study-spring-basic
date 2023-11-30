package com.example.studyspring.discount;

import com.example.studyspring.annotation.MainDiscountPolicy;
import com.example.studyspring.member.Grade;
import com.example.studyspring.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscount")
@MainDiscountPolicy // 내가 직접 만든 어노테이션 @Qualifier("mainDiscount")과 동일한 동작
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
