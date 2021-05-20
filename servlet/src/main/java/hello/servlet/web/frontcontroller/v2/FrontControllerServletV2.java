package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

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
@WebServlet(name="frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    // 매핑 정보 (URI문자열, 호출할 컨트롤러 쌍)
    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    // 생성자 : URL 에 따라 컨트롤러를 다르게 호출한다.
    public FrontControllerServletV2() { // URI키에 따라 생성되는 컨트롤러 객체가 다르다.
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        ControllerV2 controller = controllerMap.get(requestURI);

        if(controller == null){ // 예외처리
            // URI가 없으면 404
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        
        // 모든 컨트롤러는 뷰 객체를 생성하여 반환한다.
        MyView view = controller.process(request, response);
        view.render(request, response); // view를 랜더링 한다!
    }

}
