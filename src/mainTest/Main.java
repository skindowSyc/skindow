package mainTest;

import java.util.function.Consumer;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("hello");
        }).start();
        Consumer<String> con = e -> {
            System.out.println("你好" + e);
        };
        con.accept("小明");
        System.out.println("" + strHandle("name",s ->s.trim()));
    }
    private static String strHandle(String str, Function<String,Object> fun)
    {
        return fun.apply(str).toString();
    }
}
