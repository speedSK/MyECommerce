package com.ruoyi.common.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class GeneratorTest {


    public void testIdGenerator() {
        long avg = 0;
        for (int k = 0; k < 10; k++) {
            Set<Callable<Long>> partitions = new HashSet<>();
            final IdGen idGen = IdGen.get();
            for (int i = 0; i < 140; i++) {
                partitions.add(new Callable<Long>() {
                    @Override
                    public Long call() throws Exception {
                        return idGen.nextId();
                    }
                });
            }
            ExecutorService executorPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            try {
                long s = System.currentTimeMillis();
                List<Future<Long>> futures = executorPool.invokeAll(partitions, 10000, TimeUnit.SECONDS);
                for (Future<Long> i: futures) {
                    System.out.println(i.get());
                }
                long s_avg = System.currentTimeMillis() - s;
                avg += s_avg;
                System.out.println("完成时间需要: " + s_avg / 1.0e3 + "秒");
                executorPool.shutdown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("平均完成时间需要: " + avg / 10 / 1.0e3 + "秒");
    }

    public static void main(String[] args) {
        GeneratorTest generatorTest = new GeneratorTest();
        generatorTest.testIdGenerator();
    }
}

