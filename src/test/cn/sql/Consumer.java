package cn.sql;

import java.util.concurrent.BlockingQueue;

/**
 * @Author: sfpy
 * @Date: 5/20/2019 12:56 PM
 * @Descirption //消费者
 * @Version 1.0
 */
public class Consumer  implements Runnable {
    private final BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue q){
        this.queue=q;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(2000);//模拟耗时
                consume(queue.take());
            }catch (InterruptedException e){

            }

        }
    }

    private void consume(Integer n) {
        System.out.println("Thread:" + Thread.currentThread().getId() + " consume:" + n);

    }
}