package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller; // @Controller 애노테이션을 사용하지 않은 형태

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 스프링 빈의 이름으로 핸들러를 찾을 수 있는 핸들러 매핑
 *  OldController라는 스프링 빈의 이름이 "/springmvc/old-controller" URL로 정해진다.
 *
 *  [ 핸들러 매핑 인식 우선순위 ]
 *  애노테이션 기반 컨트롤러인 @RequestMapping  : RequestMappingHandlerMapping
 *  빈 이름으로 핸들러 찾기 : BeanNameUrlHandlerMapping
 *
 *  [ 핸들러 어댑터 인식 우선순위 ]
 *  애노테이션 기반 컨트롤러인 @RequestMapping : RequestMappingHandlerAdapter
 *  HttpRequestHandler 처리 (서블릿과 유사한 형태)
 *  Controller 인터페이스(과거에 사용했던 형태) : SimpleControllerHandlerAdapter
 *
 * * 실무에서는 99.9% @RequestMapping 방식의 컨트롤러를 사용한다. (핸들러 매핑과 핸들러 어댑터.)
 */

@Component("/springmvc/old-controller")
public class OldController implements Controller {   // @Controller 애노테이션을 사용하지 않은 형태
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return new ModelAndView("new-form");
    }
}
