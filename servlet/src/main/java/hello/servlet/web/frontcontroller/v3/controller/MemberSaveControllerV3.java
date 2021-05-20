package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        // 파라미터에서 저장할 것들을 꺼낸다.
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        // 멤버 객체를 레포지토리에 저장
        Member member = new Member(username, age);
        memberRepository.save(member);

        // 뷰 경로의 논리적 이름을 저장. 모델 뷰에 멤버 객체를 넣음!
        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member);

        return mv;
    }
}
