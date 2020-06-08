import java.util.concurrent.CountDownLatch;

/**
 * bxl
 * 2019/3/19 09:15
 */
public class Worker1 implements Runnable {
    private final CountDownLatch doneSingal;
    private final int i;

    public Worker1(CountDownLatch doneSingal,int i){
         this.doneSingal = doneSingal;
         this.i = i;
    }

    public void run(){
        doWork(i);
        doneSingal.countDown();
    }

    private void doWork(int i){
        System.out.println(i);
    }
}
