package ThreadLifeCycle;

import Acces.AccessModifier;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args){
//        Object lock=new Object();
//        waiterRunnable r1=new waiterRunnable(lock);
//        chefRunnable r2=new chefRunnable(lock);
//        Thread waiter=new Thread(r1);
//        Thread chef=new Thread(r2);
//        waiter.start();
//        chef.start();
//        ExecutorService service = Executors.newFixedThreadPool(3);
//        for(int i=0;i<5;i++){
//            service.submit(new ExecutorRunnable(i));
//        }
//        service.shutdown();
//        System.out.println("Graceful shutdown Initiated");
//        try {
//            if(!service.awaitTermination(10, TimeUnit.SECONDS)){
//                //service.shutdownNow();
//                List<Runnable> pendingTasks = service.shutdownNow();
//                System.out.println("Number of pending tasks that never started: " + pendingTasks.size());
//                System.out.println("Forcing Shutdown");
//            }
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        System.out.println("All Thread Terminated.");
        AccessModifier ac=new AccessModifier();
        ac.pubMethod();
        

    }
}
