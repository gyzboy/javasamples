package com.gyz.javasamples.concurrent.consumer;

public class ConsumerProductorTest {
    private final Object lock = new Object();
    private int count = 0;
    private final int MAX_COUNT = 100;

    public void consumer() {
        if (count >= 0){
            synchronized (lock) {
                while (count == 0) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                count--;
                System.out.println("ThreadName = " + Thread.currentThread().getName() + " count = " + count);
                lock.notifyAll();
            }
        }
    }

    public void productor() {
        if (count <= MAX_COUNT){
            synchronized (lock) {
                while (count == MAX_COUNT) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                count++;
                System.out.println("ThreadName = " + Thread.currentThread().getName() + " count = " + count);
                lock.notifyAll();
            }
        }

    }

    public static void main(String[] args) {
        ConsumerProductorTest t = new ConsumerProductorTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    t.productor();
                }
            }
        }, "t1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    t.consumer();
                }
            }
        }, "t2").start();
    }
}
