package singleton;

/**
 * 懒汉式，这里的写法是线程不安全的
 */
public class Singleton {

    private static Singleton singleton;
    //私有的构造函数
    private Singleton(){}
    //静态方法提供实例
    public static Singleton getInstance(){
        if(singleton == null){
            singleton = new Singleton();
        }
        return singleton;
    }
}
