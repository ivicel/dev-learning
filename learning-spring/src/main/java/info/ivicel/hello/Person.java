package info.ivicel.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//@Component
public class Person {
//    @Value("${db.classDriverName}")
    private String name;
//    @Value("${server.port}")
    private int age;
//    @Value("0117788")
    private String iDCode;

//    public Person() {
//        System.out.println("Constructor.");
//    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setiDCode(String IDCode) {
        System.out.println("hwladfjlkajflkdjsalfklo");
        this.iDCode = IDCode;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age + '\'' +
                ", iDCode=" + iDCode +
                '}';
    }

    public void sayHello() {
        System.out.println("Hello to " + this.toString());
    }

    public void throwError() {
        System.out.println("do something...");
        throw new RuntimeException();
    }
}
