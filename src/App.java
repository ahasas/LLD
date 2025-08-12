import ThreadImplementation.*;

import javax.print.DocFlavor;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
package src;
public class App {
    public static void main(String[] args) throws Exception {
//        MyThread thread1=new MyThread();
//        MyThread thread2=new MyThread();
//        thread1.start();

//        thread2.start();
//2) Thread Implemenation by runnable;
//        MythreadRunnable runnable =new MythreadRunnable();
//        Thread thread1=new Thread(runnable);
//        Thread thread2=new Thread(runnable);
//        thread1.start();
//        thread2.start();

//        ExecutorService executor= Executors.newFixedThreadPool(3);
//        MyCallable call1=new MyCallable("Test");
//        MyCallable call2=new MyCallable("Test2");
//        Future<String> f1=executor.submit(call1);
//        Future<String> f3=executor.submit(call1);
//       // executor.shutdownNow();
//        Future<String> f2=executor.submit(call2);
//
//        System.out.println(f1.get());
//
//        System.out.println(f3.get());
//        executor.shutdown();


//        MyCallable calla=new MyCallable("Test");
//        CallViaRunnable run1=new CallViaRunnable(calla);
//        Thread thread1=new Thread(run1);
//        thread1.start();
//        thread1.start();
        SharedResource s1=new SharedResource();

        Thread thread1=new Thread(s1::test,"Thread-1");

        Thread thread2=new Thread(s1::notification,"Thread-2");
        Thread thread3 =new Thread(s1::test,"Thread-3");
        thread1.start();
        Thread.sleep(1000);
        thread3.start();
        thread2.start();




    }
}
