package cn.sql;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @Author: sfpy
 * @Date: 5/20/2019 12:55 PM
 * @Descirption 生产者
 * @Version 1.0
 */
//消费者
public class Producer implements Runnable{
    private final BlockingQueue<Integer> queue;

    public Producer(BlockingQueue q){
        this.queue=q;
    }

    @Override
    public void run() {
        try {
            while (true){
                Thread.sleep(1000);//模拟耗时
                queue.put(produce());
            }
        }catch (InterruptedException e){

        }
    }

    private int produce() {
        int n=new Random().nextInt(10000);
        System.out.println("Thread:" + Thread.currentThread().getId() + " produce:" + n);
        return n;
    }
}