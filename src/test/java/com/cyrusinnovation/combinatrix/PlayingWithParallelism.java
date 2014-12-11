package com.cyrusinnovation.combinatrix;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.IntStream;

public class PlayingWithParallelism extends TestHelper {

    HashMap<Integer, String> normalMap = new HashMap<>();
    ConcurrentHashMap<Integer, String> concurrentMap = new ConcurrentHashMap<>();
    final int s = 5_000_000;

//    @Before
//    public void setUp(){
//        IntStream.rangeClosed(0,s)
//                .forEach(i -> {
//                    normalMap.put(i, "HOWDY!");
//                    concurrentMap.put(i, "HOWDY2");
//                });
//    }


    @Test
    public void testWrites(){
        long start = System.nanoTime();
        IntStream.rangeClosed(0,s)
                .forEach(i -> normalMap.put(i, "HOWDY!"));
        long duration = (System.nanoTime() - start);
        p("Regular:  ", duration);

        start = System.nanoTime();

        IntStream.rangeClosed(0,s)
                .parallel()
                .forEach(i -> concurrentMap.put(i, "HOWDY2"));


        duration = (System.nanoTime() - start);
        p("Parallel: ", duration);
    }

    @Test
    public void testParallelReads(){
        long start = System.nanoTime();
        List<String> acc = new ArrayList<>();
        IntStream.rangeClosed(0,s)
                .forEach(i -> acc.add(normalMap.get(i)));
        long duration = (System.nanoTime() - start);
        p("Regular:  ", duration);

        start = System.nanoTime();
        ConcurrentLinkedQueue<String> acc2 = new ConcurrentLinkedQueue<>();
        IntStream.rangeClosed(0,s)
                .parallel()
                .forEach(i -> acc2.add(concurrentMap.get(i)));
        duration = (System.nanoTime() - start);
        p("Parallel: ", duration);


    }



}


