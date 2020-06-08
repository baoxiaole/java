package singleton;

/**
 * 枚举，防止反序列化重新创建新的对象
 */
public enum Singleton_enum {
    INSTANCE;
    public void whateverMethod() {}
}
