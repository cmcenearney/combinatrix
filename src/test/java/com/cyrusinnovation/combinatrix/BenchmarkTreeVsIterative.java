package com.cyrusinnovation.combinatrix;

import org.apache.commons.math3.util.Combinations;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BenchmarkTreeVsIterative extends TestHelper{

    double testIter(int n, int k){
        long start = System.nanoTime();
        Iterator<int[]> a = new Combinations(n, k).iterator();
        Set<int[]> apache = new HashSet<>();
        while (a.hasNext()) {
            apache.add(a.next());
        }
        long stop = System.nanoTime();
        return (stop - start) * 1e-6;
    }

    double testTree(int n, int k){
        long start = System.nanoTime();
        BinomialTreeCombinations c = new BinomialTreeCombinations(n);
        Set<List<Integer>> combos = c.getKIndices(k);
        long stop = System.nanoTime();
        return (stop - start) * 1e-6;
    }


//
//    @Test
//    public void runBenchmarks(){
//        p("n", "\t", "k", "\t", "iter","\t", "tree");
//        for(int n = 12; n <= 16; n++){
//            for(int k = 2; k <= n; k++){
//                p(n, "\t", k, "\t", testTree(n,k),"\t", testIter(n,k));
//            }
//        }
//    }

    @Test
    public void macroBenchTree(){
        long start = System.nanoTime();
        for(int n = 2; n <= 24; n++){
            for(int k = 2; k <= n; k++){
                BinomialTreeCombinations c = new BinomialTreeCombinations(n);
                Set<List<Integer>> combos = c.getKIndices(k);
            }
        }
        long stop = System.nanoTime();
        double time = (stop - start) * 1e-6;
        p("Tree: ", time);
    }

    @Test
    public void macroBenchIter(){
        long start = System.nanoTime();
        for(int n = 2; n <= 24; n++){
            for(int k = 2; k <= n; k++){
                Iterator<int[]> a = new Combinations(n, k).iterator();
                Set<int[]> apache = new HashSet<>();
                while (a.hasNext()) {
                    apache.add(a.next());
                }
            }
        }
        long stop = System.nanoTime();
        double time = (stop - start) * 1e-6;
        p("Iter: ", time);
    }


}
