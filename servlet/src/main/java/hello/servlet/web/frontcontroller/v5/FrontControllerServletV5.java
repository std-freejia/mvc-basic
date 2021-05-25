package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 만약 어떤 개발자는 '컨트롤러 V3'방식으로 개발하고 싶고, 어떤 개발자는 '컨트롤러 V4' 방식으로 개발하고 싶다면 어떻게 해야 할까?
 *
 * 어댑터 패턴 사용하기!
 * 완전히 다른 인터페이스들을 모두 사용할 수 있도록, 프론트 컨트롤러가 다양한 방식의 컨트롤러를 처리할 수 있도록 변경해보자.
 *
 * 핸들러 어댑터 목록 : 핸들러를 처리할 수 있는 핸들러 어댑터를 조회한다.
 * 여기서 핸들러는 컨트롤러와 같은 개념이라고 이해하자.
 * 해당 핸들러를 처리할 수 있는 어댑터(핸들러 어댑터)를 통해 핸들러(컨트롤러)를 호출한다.
 */

@WebServlet(name="frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    // 매핑 URI String과 컨트롤러 객체 쌍을 저장하는 Map
    // 모든 컨트롤러를 지원해야 하니까, Object로 받는다.
    private final Map<String, Object> handlerMappingMap = new HashMap<>();

    // 여러 핸들러가 들어있고, 그 중에 하나 선택해서 쓸 거니까 List에 핸들러 어댑터를 넣어둔다.
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    // 생성자
    public FrontControllerServletV5() {
        // V3 를 지원함.
        initHandlerMappingMap();
        // 어댑터 List에 V3 컨트롤러들을 넣어준다.
        initHandlerAdapters();
    }

    // 여기 URI 주의! URI가 v5를 탄 다음, v3를
    private void initHandlerMappingMap() { // URI키에 따라 생성되는 컨트롤러 객체가 다르다.
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
    }

    private void initHandlerAdapters() { // ControllerV3HandlerAdapter 만 들어있다.
        handlerAdapters.add(new ControllerV3HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 핸들러 찾아오세요 - MemberFormControllerV3 를 받아오게 된다.
        Object handler = getHandler(request);

        if(handler == null){ // URI가 없으면 404 예외처리
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // 2. 핸들러 어댑터 찾아오세요. (해당 핸들러를 처리할 수 있는 핸들러 어댑터가 필요하다.) - ControllerV3HandlerAdapter 를 받음
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        // 3. 어댑터에 맞는 컨트롤러가 모델 뷰를 반환한다.
        ModelView mv = adapter.handle(request, response, handler);

        // 4. 논리적 이름을 물리 이름(실제 경로)로 변환 한다.
        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName); // 경로명을 넘긴다

        // 5. 모델을 넘긴다. <- getModel()에 값을 넣고 랜더링한다.
        view.render(mv.getModel(), request, response); // 랜더링
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        MyHandlerAdapter a;
        // 루프 돌려서 핸들러 어댑터를 찾아온다.
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if(adapter.supports(handler)){
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler=" + handler);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI(); // 매핑 정보 가져오기
        return handlerMappingMap.get(requestURI);  // 핸들러 매핑 맵에서 핸들러를 하나 찾아온다.
    }

    private MyView viewResolver(String viewName) { // 실제 경로로 변환
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

}
