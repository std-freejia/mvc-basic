package hello.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name="requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // request.getInputStream() : BODY 의 내용을 바이트코드로 바로 얻을 수 있다.
        ServletInputStream inputStream = request.getInputStream();
        // 바이트코드를 String 으로 바꿔야 함.(변환 시, 반드시 인코딩 명시하기)
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messageBody = " + messageBody);
        /*
        postman 에서 시험
        [POST] http://localhost:8080/request-body-string
        body의 raw 데이터로 메시지를 입력하여 보내본다.
         */
        response.getWriter().write("ok");
    }
}
