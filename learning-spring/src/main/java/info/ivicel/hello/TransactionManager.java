package info.ivicel.hello;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TransactionManager {
    private static final String EXECUTION = "execution(* info.ivicel.hello..*.*(..))";

    @Before(EXECUTION)
    public void onOpen() {
        System.out.println("onOpen()....");
    }

    @After(EXECUTION)
    public void onClose() {
        System.out.println("onClose()....");
    }

    @AfterThrowing(EXECUTION)
    public void onError() {
        System.out.println("onError()....");
    }

    @AfterReturning(EXECUTION)
    public void onCommit() {
        System.out.println("onCommit()....");
    }
}
