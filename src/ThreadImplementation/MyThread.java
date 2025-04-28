package ThreadImplementation;

public class MyThread extends Thread{
    @Override
    public void run(){

        try {
            throw new RuntimeException("Exception in thread");
        } catch (Exception e) {
            System.out.println("Caught exception in thread: " + e.getMessage());
        }
        System.out.println(Thread.currentThread().getName()+" is wake up from sleep");
    }
}
