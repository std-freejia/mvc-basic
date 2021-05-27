package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Controller
 * 1) 스프링 빈이 자동으로 SpringMemberFormControllerV1 을 빈으로 등록한다.
 *   (내부에 @Component 애노테이션이 있어서 '컴포넌트 스캔'의 대상이 되기 때문이다.)
 * 2) 애노테이션 기반 '컨트롤러'로 인식한다. - 핸들러 매핑의 대상이 된다.
 *
 * @RequestMapping
 * 1) 요청 정보를 매핑한다.
 * 해당 URL이 호출되면, 이 메서드가 호출된다. 매서드 이름은 임의로 지어도 된다.
 *
 * 핸들러로 인식하는 기준
 * 1) @Controller 를 클래스 레벨에 붙이기
 * 2) @Component 와 @RequestMapping을 클래스 레벨에 붙이기
 */

//@Controller
@Component
@RequestMapping
public class SpringMemberFormControllerV1 {


    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process(){
        return new ModelAndView("new-form");
        // 뷰 논리 이름 넘기기 (application.properties에 prefix, suffix 정의 되어 있음)
    }
}
