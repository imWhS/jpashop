package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Rollback(false)
    public void 회원_가입() throws Exception {
        Member member = new Member();
        member.setName("wonhee");

        Long savedId = memberService.join(member);

        Assertions.assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외_처리() throws Exception {
        Member member1 = new Member();
        member1.setName("son1");

        Member member2 = new Member();
        member2.setName("son1");

        memberService.join(member1);

        /*
        @Test(expected = IllegalStateException.class)로 생략 가능한 코드
        try {
            memberService.join(member2);
        } catch (IllegalStateException e) {
            //IllegalStateException 예외가 발생해서 catch되면 테스트 성공에 해당한다.
            return;
        }

        fail("IllegalStateException 예외가 발생하지 않았습니다.");
        */

        memberService.join(member1);
        memberService.join(member2);

        fail("IllegalStateException 예외가 발생하지 않았습니다.");
    }

}