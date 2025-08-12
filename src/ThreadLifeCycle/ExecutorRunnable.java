package ThreadLifeCycle;

public class ExecutorRunnable implements Runnable{
    private int taskId;

    public ExecutorRunnable(int taskId) {
        this.taskId = taskId;
    }
    @Override
    public void run(){
        System.out.println("Task Id is :"+taskId);
        try {
            Thread.sleep(5000);
            synchronized (this){
                System.out.println(Thread.currentThread().getName()+"Waiting for task with id"+taskId);
                this.wait(2000);

            }
            System.out.println(Thread.currentThread().getName()+"Completing for task with id"+taskId);

        } catch (InterruptedException e) {
            System.out.println("Interuppted");
            Thread.currentThread().interrupt();
        }
    }
}
