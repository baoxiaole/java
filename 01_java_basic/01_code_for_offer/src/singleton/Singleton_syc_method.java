package singleton;

/**
 * 懒汉式，同步方法，缺点是效率太低，每次获取实例都要进同步方法，其实只需要第一次进同步方法，后面直接返回实例，不推荐使用
 */
public class Singleton_syc_method {
    private static Singleton_syc_method singleton_syc_method;
    private Singleton_syc_method(){}
    public static synchronized Singleton_syc_method getInstance(){
        if(singleton_syc_method == null){
            singleton_syc_method = new Singleton_syc_method();
        }
        return singleton_syc_method;
    }
}
