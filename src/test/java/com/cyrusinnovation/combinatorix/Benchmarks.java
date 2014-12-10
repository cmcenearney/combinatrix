package com.cyrusinnovation.combinatorix;

import org.apache.commons.math3.util.Combinations;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Benchmarks {

    @Test
    public void timeLocal() {
        int n = 22;
        int k = 2;
        System.out.println("n = " + n + ", k = " + k);

        long start = System.nanoTime();
        Iterator<int[]> a = new Combinations(n, k).iterator();
        Set<int[]> apache = new HashSet<>();
        while (a.hasNext()) {
            apache.add(a.next());
        }
        long stop = System.nanoTime();
        long time = stop - start;
        System.out.println("iter version: " + time + ", size: " + apache.size());

        start = System.nanoTime();
        BinomialTreeCombinations c = new BinomialTreeCombinations(n);
        Set<List<Integer>> combos = c.getKIndices(k);
        stop = System.nanoTime();
        time = (stop - start);// / 1_000_000;
        System.out.println("tree version: " + time + ", size: " + combos.size());

        start = System.nanoTime();
        ParallelBTC p = new ParallelBTC(n,k);
        ConcurrentLinkedQueue<ArrayList<Integer>> pc = p.getKIndices();
        stop = System.nanoTime();
        time = stop - start;
        System.out.println("prll version: " + time + ", size: " + pc.size());

        assert (apache.size() == combos.size());
        assert (apache.size() == pc.size());
    }
}
