package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable //JPA의 내장 타입으로 선언
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    //기본 생성자
    protected Address() {
    }

    //필드 초기화 생성자
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
