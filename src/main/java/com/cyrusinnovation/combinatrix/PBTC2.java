package com.cyrusinnovation.combinatrix;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BinaryOperator;
import java.util.stream.IntStream;

public class PBTC2 {

    private final BTree root;
    private final int n;
    private final int k;
    BinaryOperator<Set<List<Integer>>> reducer = ((s1, s2) -> {s1.addAll(s2); return s1;});
    BinaryOperator<CopyOnWriteArrayList<ArrayList<Integer>>> creducer = ((s1, s2) -> {s1.addAll(s2); return s1;});

    public class BTree {
        HashMap<Integer, BTree> children;
        BTree() {
            this.children = new HashMap<>();
        }
    }

    public PBTC2(int n, int k) {
        this.n = n;
        this.k = k;
        root = new BTree();
        root.children.put(0, new BTree());
        add(n - 1);
    }

    public Set<List<Integer>>  getKIndices() {
        return targetedDLS(this.k);
    }

    private void add(int n) {
        for (int i = 1; i <= n; i++) {
            if (root.children.containsKey(i))
                continue;
            root.children.put(i, new BTree());
            BTree node = root.children.get(i);
            for (int j = 0; j < i; j++) {
                node.children.put(j, root.children.get(j));
            }
        }
    }

    //dls - depth limited search
    private Set<List<Integer>> targetedDLS(int limit) {
        Set<Set<List<Integer>>> acc = new HashSet<>();
        Set<List<Integer>> r = new HashSet<>();
        IntStream.rangeClosed(limit - 1, n - 1).boxed()
                .parallel()
                .forEach(i -> {
                    BTree node = root.children.get(i);
                    Set<List<Integer>> results = new HashSet<>();
                    ArrayList<Integer> path = new ArrayList<>();
                    path.add(i);
                    targetedDLS(node, path, results, limit - 1);
                    acc.add(results);
                });
        acc.stream().forEach(s -> r.addAll(s));
        return r;
    }

    private void targetedDLS(BTree node, ArrayList<Integer> path, Set<List<Integer>> results, int limit) {
        if (limit == 0) {
            results.add(path);
            return;
        }
        for (Integer i : node.children.keySet()) {
            BTree child = node.children.get(i);
            ArrayList<Integer> pathC = new ArrayList<>(path);
            pathC.add(0, i);
            targetedDLS(child, pathC,results, limit - 1);
        }
    }

}
