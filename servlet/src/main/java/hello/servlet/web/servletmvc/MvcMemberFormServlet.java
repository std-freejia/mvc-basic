package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 서블릿을 컨트롤러로. JSP를 뷰로 사용할 것임
@WebServlet(name="mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // URL에 따라 jsp(뷰)를 부른다.
        String viewPath = "/WEB-INF/views/new-form.jsp";
        // /WEB-INF 하위의 파일 : 반드시 컨트롤러의 포워딩을 통해서만 접근 가능한 뷰들을 위치.

        // RequestDispatcher 컨트롤러에서 뷰로 이동할 때 사용
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        // forward() : 다른 서블릿이나 jsp를 찾아서 호출. ('서버 내부'에서 다시 호출이 발생한다.)
        dispatcher.forward(request, response);
    }
}
