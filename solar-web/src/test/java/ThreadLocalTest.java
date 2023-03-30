import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author hushaoge
 * @date 2023/3/30 9:54
 */
public class ThreadLocalTest {
    public static void main(String[] args) throws InterruptedException {
        TransmittableThreadLocal<String> local = new TransmittableThreadLocal<>();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        CountDownLatch cd1 = new CountDownLatch(1);
        CountDownLatch cd2 = new CountDownLatch(1);

        local.set("主线程1");
        Executor executorService2 = TtlExecutors.getTtlExecutor(executorService);
        executorService2.execute(() -> {
            System.out.println("1-----Thread1:"+ local.get());
            cd1.countDown();
        });

        cd1.await();
        System.out.println(local.get());
        System.out.println("----------------------------分割线------------------------------");
        local.remove();
        local.set("主线程2");

        executorService2.execute(() -> {
            System.out.println("2-----Thread2:"+local.get());
            cd2.countDown();
        });

        cd2.await();

        System.out.println(local.get());
        executorService.shutdown();
    }
}
