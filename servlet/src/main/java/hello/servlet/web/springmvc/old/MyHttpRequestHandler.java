package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 *  MyHttpRequestHandler 라는 스프링 빈의 이름이 "/springmvc/request-handler" URL로 정해진다.
 *
 *  [ 핸들러 매핑 ]
 *  빈 이름으로 핸들러 찾기 : BeanNameUrlHandlerMapping
 *
 *  [ 핸들러 어댑터 ]
 *  HttpRequestHandlerAdapter가 처리해줌 (서블릿과 유사한 형태)
 */
@Component("/springmvc/request-handler")
public class MyHttpRequestHandler implements HttpRequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MyHttpRequestHandler.handleRequest");;
    }

}
