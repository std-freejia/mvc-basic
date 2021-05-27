package hello.servlet.web.springmvc.v2;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 연관성이 있는 컨트롤러 끼리 묶어서 메소드를 모아 만들 수 있다.
 */
@Controller
@RequestMapping("/springmvc/v2/members")  // - 공통된 URL 작성
public class SpringMemberControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    //@RequestMapping("/springmvc/v2/members/new-form") -- 공통된 URL을 제거
    @RequestMapping("/new-form")
    public ModelAndView newForm(){
        return new ModelAndView("new-form");
        // 뷰 논리 이름 넘기기 (application.properties에 prefix, suffix 정의 되어 있음)
    }

    //@RequestMapping("/springmvc/v2/members/save")
    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", member);

        return mv;
    }

    //@RequestMapping("/springmvc/v2/members")
    @RequestMapping
    public ModelAndView members(){

        List<Member> members = memberRepository.findAll();

        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members", members);

        return mv;
    }
}
