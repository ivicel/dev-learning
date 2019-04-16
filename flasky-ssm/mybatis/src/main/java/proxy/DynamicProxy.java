package proxy;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.charset.Charset;
import java.util.Arrays;

public class DynamicProxy implements InvocationHandler {
    private HelloImpl hello;
    public DynamicProxy(HelloImpl hello) {
        this.hello = hello;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理调用前...");
        Object object = method.invoke(hello, args);
        System.out.println("代理调用后...");
        return object;
    }

    public static void main(String[] args) {
        System.out.println("\u00c5");
        System.out.println("\u0041");
        System.out.println("\u030a");
        System.out.println("\u0041\u030a");
    }

}
