package com.cyrusinnovation.combinatrix;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.junit.Assert.assertEquals;

public class ParallelBTCTest extends TestHelper {

    @Test
    public void testK3N72(){
        ParallelBTC c = new ParallelBTC(72,3);
        Set<List<Integer>> combos = c.getKIndicesAsSet();
        //System.out.println(combos.size());
        assert(59640 == combos.size());
    }

    @Test
    public void testN1000(){
        ParallelBTC c = new ParallelBTC(1000,3);
        ConcurrentLinkedQueue<ArrayList<Integer>> kInds = c.getKIndices();
        //Set<List<Integer>> combos = c.getKIndicesAsSet();
        //System.out.println(combos.size());
        //assert(59640 == combos.size());
    }

    @Test
    public void testK3N5() {
        ParallelBTC c = new ParallelBTC(5,3);
        Set<List<Integer>> combos = c.getKIndicesAsSet();
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