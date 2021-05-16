package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// @WebServlet : 서블릿 이름과 URL을 정한다. 서블릿 이름과 URL매핑은 유일해야 한다.
@WebServlet(name="helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    // control + R : 서버 재시작
    @Override // URL이 호출되면, 서블릿 컨테이너는 service()메서드를 실행한다.
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        // 쿼리 파라미터 http://localhost:8080/hello?username=hi
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        // http 응답(response) 메시지에 내용이 담긴다.
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        // http message body 에 메시지가 들어감
        response.getWriter().write("hello"+username);

    }
}
