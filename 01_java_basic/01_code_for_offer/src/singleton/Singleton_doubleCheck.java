package singleton;

public class Singleton_doubleCheck {
//    private static Singleton_doubleCheck singleton_doubleCheck;
    //看了一些文章，这里使用volatile来避免指令重排
    private static volatile Singleton_doubleCheck singleton_doubleCheck;
    private Singleton_doubleCheck(){}
    public static Singleton_doubleCheck getInstance(){
        if(singleton_doubleCheck == null){
            synchronized (Singleton_doubleCheck.class){
                if(singleton_doubleCheck == null){
                    singleton_doubleCheck = new Singleton_doubleCheck();
                }
            }
        }
        return singleton_doubleCheck;
    }
}
