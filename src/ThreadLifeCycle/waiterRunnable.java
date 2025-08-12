package ThreadLifeCycle;

public class waiterRunnable implements Runnable{
    private Object lock;
    public waiterRunnable(Object lock){
        this.lock=lock;
    }

    @Override
    public void run(){
        System.out.println("Takes Order from Customer");
        try {

            synchronized (lock){
                Thread.sleep(20000);
                lock.wait();
                System.out.println("Food delieverd to customer");

            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
