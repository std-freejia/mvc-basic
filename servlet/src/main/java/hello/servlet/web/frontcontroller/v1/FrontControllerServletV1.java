package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

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
@WebServlet(name="frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    // 매핑 정보 (URI문자열, 호출할 컨트롤러 쌍)
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    // 생성자 : URL 에 따라 컨트롤러를 다르게 호출한다.
    public FrontControllerServletV1() { // URI키에 따라 생성되는 컨트롤러 객체가 다르다.
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        String requestURI = request.getRequestURI();

        // URI에서 문자열을 읽어와서 ControllerMap에 가져옴.
        ControllerV1 controller = controllerMap.get(requestURI); // 부모는 자식을 담을 수 있다. (다형성)

        if(controller == null){ // 예외처리
            // URI가 없으면 404
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        controller.process(request, response);
    }

}
