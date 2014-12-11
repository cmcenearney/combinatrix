package com.cyrusinnovation.combinatrix;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PlayingWithParallelism2 extends TestHelper {

    class Item{
        String label = "HI";
        int i;
        Item(int i){this.i = i;}
        int getI(){return this.i;}
    }

    List<Item> items;
    final int s = 40_000;

    @Before
    public void setUp(){
        items = new ArrayList<>();
        IntStream.rangeClosed(0, s).forEach(i -> items.add(new Item(i)));
    }

    @Test
    public void inspectCollectors(){
        Collectors.toSet().characteristics().stream().forEach(c -> p(c));
    }

    @Test
    public void timeNonConcurrentGrouping() {
        long start = System.nanoTime();
        Map<Integer, List<Item>> byI =
                items
                        .stream()
                        .collect(
                                Collectors.groupingBy(Item::getI));
        long stop = System.nanoTime();
        double time = (stop - start) * 1e-6;
        p("not concurrent: ",time);
    }

    @Test
    public void timeConcurrentGrouping() {
        long start = System.nanoTime();
        ConcurrentMap<Integer, List<Item>> byI =
                items
                        .parallelStream()
                        .collect(
                                Collectors.groupingByConcurrent(Item::getI));
        long stop = System.nanoTime();
        double time = (stop - start) * 1e-6;
        p("concurrent: ",time);
    }

}
