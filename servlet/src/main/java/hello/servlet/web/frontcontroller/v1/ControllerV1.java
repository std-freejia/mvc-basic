package hello.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV1 { // 컨트롤러는 인터페이스로 만든다.

    // 각 컨트롤러들은 이 인터페이스를 구현하면 된다.
    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
