package hello.core.singleton;

public class SingletonServie {

    private static final  SingletonServie instance = new SingletonServie();

    public static SingletonServie getInstance() {
        return instance;
    }

    private SingletonServie() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
