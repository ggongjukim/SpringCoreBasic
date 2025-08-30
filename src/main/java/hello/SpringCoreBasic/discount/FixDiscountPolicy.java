package hello.SpringCoreBasic.discount;

import hello.SpringCoreBasic.member.Grade;
import hello.SpringCoreBasic.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000; // 천원 할인

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return discountFixAmount;
        }
        else{
            return 0;
        }


        return 0;
    }
}
