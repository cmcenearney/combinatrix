package com.cyrusinnovation.combinatrix;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class PBTC2Test extends TestHelper {

    @Test
    public void testK3N5() {
        PBTC2 c = new PBTC2(5,3);
        Set<List<Integer>>  combos = c.getKIndices();
        Set<List<Integer>> expected = new HashSet<List<Integer>>(){{
            add(Arrays.asList(0, 1, 2));
            add(Arrays.asList(0, 1, 3));
            add(Arrays.asList(0, 1, 4));
            add(Arrays.asList(0, 2, 3));
            add(Arrays.asList(0, 2, 4));
            add(Arrays.asList(0, 3, 4));
            add(Arrays.asList(1, 2, 3));
            add(Arrays.asList(1, 2, 4));
            add(Arrays.asList(1, 3, 4));
            add(Arrays.asList(2, 3, 4));
        }};
        assertEquals(expected, combos);
    }

    @Test
    public void timeLocal() {
        int n = 122;
        int k = 5;
        System.out.println("n = " + n + ", k = " + k);

//        long start = System.nanoTime();
//        Iterator<int[]> a = new Combinations(n, k).iterator();
//        Set<int[]> apache = new HashSet<>();
//        while (a.hasNext()) {
//            apache.add(a.next());
//        }
//        long stop = System.nanoTime();
//        long time = stop - start;
//        System.out.println("iter version: " + time + ", size: " + apache.size());

        long start = System.nanoTime();
        PBTC2 c = new PBTC2(n,k);
        Set<List<Integer>>  combos = c.getKIndices();
        long stop = System.nanoTime();
        double time = stop - start;
        System.out.println("tree version: " + time + ", size: " + combos.size());
        //assert (apache.size() == combos.size());
    }


}