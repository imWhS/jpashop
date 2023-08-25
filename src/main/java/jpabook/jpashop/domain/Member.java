package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") //Order의 member 필드에 의해 매핑되어지는 필드
    private List<Order> orders = new ArrayList<>();

    /*
    양방향 연관 관계 설정 편의 메서드
     */

    void addOrder(Order order) {
        this.orders.add(order);
        order.setMember(this);
    }
}
