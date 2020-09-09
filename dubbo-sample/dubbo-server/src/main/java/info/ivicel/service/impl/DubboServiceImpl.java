package info.ivicel.service.impl;

import info.ivicel.service.DubboService;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;


public class DubboServiceImpl implements DubboService {
    private static final Logger LOGGER = Logger.getLogger(DubboServiceImpl.class.getSimpleName());

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

    @Override
    public CompletableFuture<String> sayHelloAsync(String name) {
        return CompletableFuture.supplyAsync(() -> "async result");
    }
}
