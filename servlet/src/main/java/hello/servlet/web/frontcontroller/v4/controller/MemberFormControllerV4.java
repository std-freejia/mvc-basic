package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4 {
    // 프론트 컨트롤러에서 데이터가 담긴 model을 process()에 파라미터로 넘겨줄 것이다.
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        return "new-form"; // 뷰의 논리 이름 넘겨주기!
    }
}
