package singleton;

/**
 * 饿汉式，在类装载的时候就完成了实例化，缺点是如果一直未用到这个类，会浪费内存。
 */
public class Singleton_hungry {
    private final static Singleton_hungry SINGLETON = new Singleton_hungry();

    //或者用静态代码块
//    private static Singleton_hungry singleton_hungry;
//    static {
//        singleton_hungry = new Singleton_hungry();
//    }

    private Singleton_hungry(){}

    public static Singleton_hungry getInstance(){
        return SINGLETON;
    }
}
