package hello.SpringCoreBasic.discount;

import hello.SpringCoreBasic.member.Member;

    /*
    * @return 할인 대상 금액 반환
    * */
public interface DiscountPolicy {
    int discount(Member member, int price);

}
