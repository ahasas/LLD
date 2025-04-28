package ThreadImplementation;

import java.util.concurrent.Callable;

public class CallViaRunnable implements Runnable{
    private Callable<String> call1;

    public CallViaRunnable(Callable<String> call1) {
        this.call1 = call1;
    }

    @Override
    public void run(){
        try {
            System.out.println(call1.call());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
