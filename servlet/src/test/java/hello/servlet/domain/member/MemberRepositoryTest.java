package hello.servlet.domain.member;

import org.assertj.core.api.AbstractIntegerAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

// static import 편함!
import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest {

     // 싱글턴이니까, new 하지 말기.
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach // 테스트 @Test가 '끝날 떄 마다' 실행 되는 어노테이션.
    void afterEach(){ 
        memberRepository.clearStore();
    }

    @Test
    void save(){
        // given : 주어진 조건
        Member member = new Member("hello", 20);

        // when : 실행할 내용
        Member savedMember = memberRepository.save(member);

        // then : 결과 (찾아낸 멤버는 주어진 멤버와 같은지 확인 )
        Member findMember = memberRepository.findById(savedMember.getId()); // 저장된 멤버의 아이디
        assertThat(findMember).isEqualTo(savedMember); // 찾아낸 멤버가 저장된 멤버와 같은가요?
    }

    @Test
    void findAll(){
        // given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1); // 2명 모두 저장
        memberRepository.save(member2);

        // when
        List<Member> result = memberRepository.findAll();
        // then
        assertThat(result.size()).isEqualTo(2); //import static org.assertj.core.api.Assertions.assertThat;
        assertThat(result).contains(member1, member2); // 결과 안에 1과 2가 있는지 확인.
    }
}
