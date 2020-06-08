import java.util.concurrent.CountDownLatch;

/**
 * bxl
 * 2019/3/18 17:06
 */
public class Worker implements Runnable{
    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;
    Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }

    public void run(){
        try{
            startSignal.await();
            doWork();
            doneSignal.countDown();
        }catch (InterruptedException ex){

        }
    }

   public void doWork() { System.out.println("doWork"); }
}
