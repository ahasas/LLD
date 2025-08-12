package ThreadLifeCycle;

public class chefRunnable implements Runnable{
    private Object lock;
    public chefRunnable(Object lock){
        this.lock=lock;
    }
    @Override
    public void run(){

        //Thread.sleep(5000);
        System.out.println("Waiting for waiter Thread");
        synchronized (lock) {
            System.out.println("Chef is cooking");
            lock.notify();
        }
    }
}
