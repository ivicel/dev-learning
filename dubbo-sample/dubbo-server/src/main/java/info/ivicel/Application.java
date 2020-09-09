package info.ivicel;

import java.io.IOException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("dubbo.xml");
        ctx.start();
        try {
            // DubboService dubboService = ctx.getBean(DubboService.class);
        // System.out.println(dubboService.sayHello("hellohello"));
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
