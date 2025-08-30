package hello.SpringCoreBasic;

import hello.SpringCoreBasic.member.Grade;
import hello.SpringCoreBasic.member.Member;
import hello.SpringCoreBasic.member.MemberService;
import hello.SpringCoreBasic.member.MemberServiceImpl;
import hello.SpringCoreBasic.order.Order;
import hello.SpringCoreBasic.order.OrderService;
import hello.SpringCoreBasic.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order.toString());
        System.out.println("order = " + order.calculatePrice()); // 9000 잘 나오는 것 확인
    }
}
