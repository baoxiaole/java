import com.spring.demo.User;
import com.spring.demo.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //1。hello world
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        userDao.say();

        //2.默认构造实例化
        User user = (User)applicationContext.getBean("user");
        user.sayHello();

        //3.指定构造器实例化
        User user1 = (User)applicationContext.getBean("user1");
        user1.sayHello();

    }
}
