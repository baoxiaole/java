import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * bxl
 * 2019/3/19 09:15
 */
public class Driver1 {

    public static void main(String[] args) throws InterruptedException {
        int n = 10;
        CountDownLatch doneSingal = new CountDownLatch(n);
        Executor e = Executors.newFixedThreadPool(5);
        for(int i=0;i<n;i++){
            e.execute(new Worker1(doneSingal,i));
        }
        doneSingal.await();
        System.out.println("完成");
    }
}
