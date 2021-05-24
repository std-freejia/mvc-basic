package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        // 파라미터에서 저장할 것들을 꺼낸다.
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        // 멤버 객체 만들어서, 이름과 나이 넣기.
        Member member = new Member(username, age);
        memberRepository.save(member); // 레포지토리에 저장.

        // 모델에 넣기
        model.put("member", member);
        return "save-result"; // 뷰의 논리 이름 넘겨주기!
    }
}
