package info.ivicel.hello.impl.subpackage;

import org.springframework.stereotype.Component;

@Component
public class Show {
    public void show() {
        System.out.println("showing.....");
    }

    public void showError() {
        System.out.println("show error");
        throw new RuntimeException();
    }
}
