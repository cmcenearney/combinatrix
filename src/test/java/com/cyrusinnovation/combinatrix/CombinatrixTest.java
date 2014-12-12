package com.cyrusinnovation.combinatrix;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class CombinatrixTest extends TestHelper {

    @Test
    public void testGetKCombinationsItems() {
        int n = 5;
        int k = 3;
        List<Integer> testItems = IntStream.range(0, 10).boxed().collect(Collectors.toList());
        Set<List<Integer>> combos = new Combinatrix<>(n,k,testItems).hurtMe();
        Set<ArrayList<Integer>> expected = new HashSet<>(Arrays.asList(
                new ArrayList<Integer>(Arrays.asList(0, 1, 2)),
                new ArrayList<Integer>(Arrays.asList(0, 1, 3)),
                new ArrayList<Integer>(Arrays.asList(0, 1, 4)),
                new ArrayList<Integer>(Arrays.asList(0, 2, 3)),
                new ArrayList<Integer>(Arrays.asList(0, 2, 4)),
                new ArrayList<Integer>(Arrays.asList(0, 3, 4)),
                new ArrayList<Integer>(Arrays.asList(1, 2, 3)),
                new ArrayList<Integer>(Arrays.asList(1, 2, 4)),
                new ArrayList<Integer>(Arrays.asList(1, 3, 4)),
                new ArrayList<Integer>(Arrays.asList(2, 3, 4))
        ));
        assertEquals(expected, combos);
    }

    @Test
    public void testK3N5getIndices() {
        int n = 5;
        int k = 3;
        List<Integer> testItems = IntStream.range(0, 10).boxed().collect(Collectors.toList());
        Set<List<Integer>> combos = new Combinatrix<>(n,k,testItems).safetyWord();
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
}