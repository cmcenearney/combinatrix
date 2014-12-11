package com.cyrusinnovation.combinatrix;

import org.apache.commons.math3.util.Combinations;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Benchmarks extends TestHelper {


    @Test
    public void timeLocal() {
        int n = 16;
        int k = 3;
        System.out.println("n = " + n + ", k = " + k);

        long start = System.nanoTime();

        BinomialTreeCombinations c = new BinomialTreeCombinations(n);
        Set<List<Integer>> combos = c.getKIndices(k);



        long stop = System.nanoTime();
        double time = (stop - start) * 1e-6;//((t2 - t1) * 1e-6) + " milliseconds"
        System.out.println("tree version: " + time + ", size: " + combos.size());

        start = System.nanoTime();

        Iterator<int[]> a = new Combinations(n, k).iterator();
        Set<int[]> apache = new HashSet<>();
        while (a.hasNext()) {
            apache.add(a.next());
        }

        stop = System.nanoTime();
        time =  (stop - start) * 1e-6;
        System.out.println("iter version: " + time + ", size: " + apache.size());


//        start = System.nanoTime();
//        ParallelBTC p = new ParallelBTC(n,k);
//        ConcurrentLinkedQueue<ArrayList<Integer>> pc = p.getKIndices();
//        stop = System.nanoTime();
//        time =  (stop - start) * 1e-6;
//        System.out.println("prll version: " + time + ", size: " + pc.size());

        assert (apache.size() == combos.size());
        //assert (apache.size() == pc.size());
    }

    @Test
    public void timeApacheLargeN() {
        int n = 100;
        int k = 5;
        System.out.println("n = " + n + ", k = " + k);

        long start = System.nanoTime();
//        BinomialTreeCombinations c = new BinomialTreeCombinations(n);
        Iterator<int[]> a = new Combinations(n, k).iterator();
        Set<int[]> apache = new HashSet<>();
        while (a.hasNext()) {
            apache.add(a.next());
        }
        long stop = System.nanoTime();
        double time = (stop - start) * 1e-6;
        System.out.println("iter version: " + time + ", size: "); //+ apache.size());
    }

    @Test
    public void nkNumbers(){
        CombinatoricsUtils.binomialCoefficientDouble(100,5);
    }

}
