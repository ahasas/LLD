package DesignPattern;

public class Singleton {

    //Eager Initialisation

//    private static final Singleton instance=new Singleton();
//
//    private Singleton(){}
//
//    public static Singleton getInstance(){
//        return instance;
//    }\\
    //Lazy Initialisation

    private static volatile Singleton instance;

    private Singleton(){}

    public static Singleton getInstance(){
        if(instance==null){
            synchronized (Singleton.class){
                if(instance==null){
                    instance=new Singleton();
                }
            }
        }
        return instance;
    }

}
