package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        // handler가 넘어오면, 이 handler가 V3컨트롤러 인스턴스 인지 참/거짓 반환
        return (handler instanceof ControllerV3);
    }

    // supports가 이미 걸러줬기 때문에, handle() 내부에서 쓰는 파라미터 handler는 V3인스턴스로 확정이 된 것임.
    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        // MemberFormControllerV3
        ControllerV3 controller = (ControllerV3) handler;

        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap); // 실제 컨트롤러가 호출되서 모델뷰를 리턴해준다.

        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        // 1. request에서 모든 파라미터 이름을 다 가져온다.
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName->paramMap.put(paramName, request.getParameter(paramName)));
        // 2. 꺼낼 파라미터 이름은(paramMap의 key) paramName으로 둔다.
        // 3. 반복문을 돌면서, request에서 꺼낸 파라미터 이름을 paramMap에 넣는다.
        return paramMap;
    }
}
