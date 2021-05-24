package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* 프론트 컨트롤러
  /front-controller/v1를 포함한 모든 하위 호출은 모두 이 서블릿에서 받아들여서 URI에 맞게 컨트롤러 객체를 생성한다.
*/
@WebServlet(name="frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    // 매핑 정보 (URI문자열, 호출할 컨트롤러 )
    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    // 생성자 : URL 에 따라 컨트롤러를 다르게 호출한다.
    public FrontControllerServletV4() { // URI키에 따라 생성되는 컨트롤러 객체가 다르다.
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        ControllerV4 controller = controllerMap.get(requestURI); // 매핑 정보 가져오기
        if(controller == null){ // 예외처리
            // URI가 없으면 404
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // request 에서 getParameter()를 모두 꺼내야 한다.
        Map<String, String> paramMap = createParamMap(request);
        // 빈 모델 만들기
        Map<String, Object> model = new HashMap<>();
        // 모델 넘겨서 뷰의 논리 경로 가져오기
        String viewName = controller.process(paramMap, model);

        MyView view = viewResolver(viewName); // 절대 경로로 변환
        view.render(model,  request, response); // 랜더링

    }

    private MyView viewResolver(String viewName) { // 실제 경로로 변환
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
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
