import java.util.concurrent.CountDownLatch;

/**
 * bxl
 * 2019/3/18 16:38
 */
public class Driver {

    public static void main(String[] args) throws InterruptedException {
        int n = 10;
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(10);
        for(int i=0;i<n;i++){
            new Thread(new Worker(startSignal, doneSignal)).start();
        }

        doSomethingElse();
        startSignal.countDown();
        doSomethingElse();
        doneSignal.await();
        System.out.println("结束");
    }

    public static void doSomethingElse(){System.out.println("doSomethingElse");}
}
