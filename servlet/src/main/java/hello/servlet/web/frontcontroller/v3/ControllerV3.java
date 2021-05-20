package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    // 뷰 경로의 논리적인 이름을 받는다.
    ModelView process(Map<String, String> paramMap);
    // v2 의 컨트롤러와는 달리, 서블릿의 종속성을 제거했다.
}
