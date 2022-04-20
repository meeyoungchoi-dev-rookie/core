package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LodDemoService lodDemoService;
    private final MyLogger myLogger;
    // 에러가 나는 이유 MyLogger는 reqeust scope이다
    // 클라이언트 요청이 들어와야만 빈을 끌어올수 있다
    // 클라이언트가 요청을 보내지 않은 상태에서는 빈을 끌어올 수 없으므로 에러가 발생한다
    // 스프링 컨테이너가 뜨는 시점에는 request가 없는 상태이기 때문이다
    // 즉 , reqeust 스코프가 생명주기가 아닌데 의존관계를 주입받으려고 하기 때문에 에러가 발생한다
    // Error creating bean with name 'myLogger': Scope 'request' is not active for the current thread;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURI().toString();
        myLogger.setRequestUrl(requestURL);

        myLogger.log("controller test");
        lodDemoService.logic("testId");
        return "OK";
    }


}
