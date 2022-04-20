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
    // class hello.core.common.MyLogger$$EnhancerBySpringCGLIB$$d33dfd68
    // CGLIB라는 라이브러리를 사용하여 가짜 프록시 객체를 만들어서 일단 주입해 둔다

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURI().toString();

        System.out.println("myLogger = " + myLogger.getClass());

        // 실제 MyLogger 빈을 사용하는 시점에 진짜 빈이 동작한다
        myLogger.setRequestUrl(requestURL);

        myLogger.log("controller test");
        Thread.sleep(100);
        lodDemoService.logic("testId");
        return "OK";
    }


}
