package singleton;

/**
 * 静态内部类
 */
public class Singleton_innerClass {
    private Singleton_innerClass(){}
    private static class SingletonInstance{
        private static final Singleton_innerClass INSTANCE = new Singleton_innerClass();
    }
    public static Singleton_innerClass getInstance(){
        return SingletonInstance.INSTANCE;
    }
}
