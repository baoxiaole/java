import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 模拟使用ConcurrentHashMap统计url访问量
 * bxl
 * 2019/2/28 14:54
 */
public class CounterDemo {

    private final Map<String,Long> urlCounter = new ConcurrentHashMap<>();

    //调用次数加1.....在多线程的情况下，可能出现值被覆盖的情况
    public Long increase(String url){
        Long oldValue = urlCounter.get(url);
        Long newValue = oldValue == null?1L:oldValue+1;
        urlCounter.put(url,newValue);
        return newValue;
    }

    public Long increase1(String url){
        Long oldValue,newValue;
        while(true){
            oldValue = urlCounter.get(url);
            if(oldValue == null){
                newValue = 1L;
                if(urlCounter.putIfAbsent(url,1L) == null)
                    //初始化成功，跳出循环
                    break;
            }else{
                newValue = oldValue + 1;
                if(urlCounter.replace(url,oldValue,newValue))
                    //更新次数成功，跳出循环
                    break;
            }
        }
        return newValue;
    }

    //获取调用次数
    public Long getCount(String url){
        return urlCounter.get(url);
    }

    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        final CounterDemo counterDemo = new CounterDemo();
        int callTimes = 100000;
        final String url = "http://localhost:8080/hello";
        CountDownLatch countDownLatch = new CountDownLatch(callTimes);
        //模拟并发情况下的接口调用
        for(int i=0;i<callTimes;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    counterDemo.increase1(url);
                    countDownLatch.countDown();
                }
            });
        }
        try {
            /**
             * Causes the current thread to wait until the latch has counted down to
             * zero, unless the thread is interrupted.
             */
            countDownLatch.await();
        }catch (Exception e){
            e.printStackTrace();
        }
        executorService.shutdown();
        //等待所有线程统计完成后输出调用次数
        System.out.println("调用次数："+counterDemo.getCount(url));
    }

}
