package ThreadImplementation;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {


    private String name;
    public MyCallable(String name) {
        this.name = name;
    }

    @Override
    public String call() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() +"print "+ "\n"+name);
        Thread.sleep(1000);
        return name+"is Valid";
    }
}
