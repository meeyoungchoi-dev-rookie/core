package hello.core.lifecyle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetwrokClient{

    private String url;

    public NetwrokClient() {
        System.out.println("생성자 호출 url = " + url);

    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스를 시작할 때 호출하는 메서드
    public void connect() {
        System.out.println("connect = " + url);
    }

    public void call(String message) {
        System.out.println("call : " + url + " message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    // 의존관계 주입이 끝나면 호출되는 메서드
    @PostConstruct
    public void init() throws Exception {
        System.out.println("NetwrokClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    // 빈이 종료될때 호출되는 메서드
    @PreDestroy
    public void close() throws Exception {
        System.out.println("NetwrokClient.close");
        disconnect();
    }
}
