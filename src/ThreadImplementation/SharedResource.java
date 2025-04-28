package ThreadImplementation;

public class SharedResource {

    public synchronized void test(){
        System.out.println(Thread.currentThread().getName() +"is RUnning");
        try {
            wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() +"wakes up from wait and now is RUnning");
    }

    public synchronized void notification(){
        System.out.println(Thread.currentThread().getName()+"breaks thread wait and notify\n");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName()+"wakes from sleep amd notify");

        notifyAll();
    }

}
