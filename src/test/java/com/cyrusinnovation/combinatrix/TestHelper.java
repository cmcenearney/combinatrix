package com.cyrusinnovation.combinatrix;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestHelper {

    public void p(Object... args){
        String s = "";
        for (Object o : args){
            s += o.toString();
        }
        System.out.println(s);
    }

    public int[] listToArray(List<Integer> l){
        int[] r = new int[l.size()];
        IntStream.range(0, l.size() - 1).forEach(i -> r[i] = l.get(i));
        return r;
    }

    public List<Integer> arrayToList(int[] r){
        return IntStream.range(0, r.length).boxed()
                .map(i -> r[i])
                .collect(Collectors.toList());
    }
}
