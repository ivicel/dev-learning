package info.ivicel.service;

import java.util.concurrent.CompletableFuture;

public interface DubboService {
    String sayHello(String name);

    CompletableFuture<String> sayHelloAsync(String name);
}
