package hello.servlet.web.frontcontroller;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter @Setter //lombok
public class ModelView {

    private String viewName; // 뷰의 논리적 이름.
    private Map<String, Object> model = new HashMap<>(); // 뷰를 랜더링 할 때 필요한 모델 객체

    // 뷰에 뿌려줄 데이터를 단순히 key, value 쌍으로 저장

    public ModelView(String viewName) {
        this.viewName = viewName;
    }
}
