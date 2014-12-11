package com.cyrusinnovation.combinatrix;

import org.apache.commons.math3.util.Combinations;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class BinomialTreeCombinationsTest extends TestHelper{

    @Test
    public void testNKPerms(){
        assert(10 == BinomialTreeCombinations.nkCombosCount(5,3));
        assert(495 == BinomialTreeCombinations.nkCombosCount(12,4));
    }

    @Test
    public void testAllPathsGenerated() {
        BinomialTreeCombinations c = new BinomialTreeCombinations(4);
        Set<List<Integer>> combos = c.getAllPaths();
        HashSet<List<Integer>> expected = new HashSet<List<Integer>>() {{
            add(Arrays.asList(0));
            add(Arrays.asList(1, 0));
            add(Arrays.asList(2, 0));
            add(Arrays.asList(2, 1, 0));
            add(Arrays.asList(3, 0));
            add(Arrays.asList(3, 1, 0));
            add(Arrays.asList(3, 2, 0));
            add(Arrays.asList(3, 2, 1, 0));
        }};
        assertEquals(expected, combos);
    }

    @Test
    public void testK3N5() {
        BinomialTreeCombinations c = new BinomialTreeCombinations(5);
        Set<List<Integer>> combos = c.getKIndices(3);
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

    //this should have a reasonable running time
    @Test
    public void testK3N72(){
        BinomialTreeCombinations c = new BinomialTreeCombinations(172);
        Set<List<Integer>> combos = c.getKIndices(3);
        assert(833340 == combos.size());
    }

    @Test
    public void testCompareWithApacheCommons(){
        BinomialTreeCombinations c = new BinomialTreeCombinations(5);
        Set<List<Integer>> biTreeCombos = c.getKIndices(3);
        Iterator<int[]> a = new Combinations(5,3).iterator();
        Set<List<Integer>> apache = new HashSet<>();
        while (a.hasNext()){
            apache.add(arrayToList( a.next()));
        }
        assertEquals(apache, biTreeCombos);
    }

    @Test
    public void timeLocal() {
        int n = 12;
        int k = 7;
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
        time = stop - start;
        System.out.println("tree version: " + time + ", size: " + combos.size());
        assert (apache.size() == combos.size());
    }


}
