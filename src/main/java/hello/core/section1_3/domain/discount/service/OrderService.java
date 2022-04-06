package hello.core.section1_3.domain.discount.service;

import hello.core.section1_3.domain.discount.entity.Order;
import hello.core.section1_3.domain.member.entity.MemberLevel;

public interface OrderService {
    MemberLevel findMemberLevel(Long memberSequence);
    int VIPDiscount(Order order);

    int NoneDiscount(Order order);
}



