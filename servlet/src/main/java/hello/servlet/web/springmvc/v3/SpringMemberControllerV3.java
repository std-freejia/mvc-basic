package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * RequestMapping
 * 1) String 으로 뷰 논리이름 반환 가능
 * 2) 파라미터로 @RequestParam 받기 가능
 * 3) GET, POST 호출을 제한 가능 (더 좋은 설계)
 *   method = RequestMethod.POST  // POST로만 호출 가능하도록.
 * 4) method를 적지 않고, @GetMapping, @PostMapping 애노테이션을 써도 된다.
 */
@Controller
@RequestMapping("/springmvc/v3/members")  // - 공통된 URL 작성
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    //@RequestMapping(value = "/new-form", method = RequestMethod.GET) // GET으로만 호출 가능!
    @GetMapping("/new-form")
    public String newForm(){
        return "new-form"; // 뷰 이름으로 인식함!!
    }


    //@RequestMapping(value = "/save", method = RequestMethod.POST)
    @PostMapping("/save")
    public String save( // 파라미터를 직접 받을 수 있음
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model
    ) { /*
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        */
        Member member = new Member(username, age);
        memberRepository.save(member);
        // 모델에 담기
        model.addAttribute("member", member);
        return "save-result";
    }


    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String members(Model model){

        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);

        return "members";
    }
}

