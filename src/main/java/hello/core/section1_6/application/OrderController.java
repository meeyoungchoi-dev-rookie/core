package hello.core.section1_6.application;


import hello.core.section1_6.member.entity.Member;
import hello.core.section1_6.member.entity.MemberLevel;
import hello.core.section1_6.order.entity.Order;
import hello.core.section1_6.order.service.OrderService;
import hello.core.section1_6.order.service.impl.OrderServiceImpl;

public class OrderController {
    private static OrderService orderService = new OrderServiceImpl();

    public static void main(String[] args) {

        // 회원 객체 생성
        Member member = new Member(null, "userId1", "userPassword1", MemberLevel.VIP);
        Member member2 = new Member(null, "userId2", "userPassword2", MemberLevel.BEGINNER);

        // 주문 생성
        Order order = new Order("product1", 2, 20000, 0, 0);
        Order order2 = new Order("product2", 4, 10000, 0, 0);

        // 회원 등급에따른 할인 금액 판단 및 주문 진행 및 결화 확인
        Order result = orderService.makeOrder(order, member);
        System.out.println("할인 전 금액: " + order.getPrice() * order.getItemCount());
        System.out.println("회원 등급:  " + member.getMemberLevel().toString() + " " + member.getMemberId() + "님의 최종 결제 금액: " + result.getDiscountPrice());

        Order result2 = orderService.makeOrder(order2, member2);
        System.out.println("할인 전 금액: " + order2.getPrice() * order2.getItemCount());
        System.out.println("회원등급:  " +  member2.getMemberLevel().toString() + " " + member2.getMemberId() + "님의 최종 결제 금액: " + result2.getDiscountPrice());






        
    }
}
