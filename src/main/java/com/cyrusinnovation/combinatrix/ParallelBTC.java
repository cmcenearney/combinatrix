package com.cyrusinnovation.combinatrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParallelBTC {

    private final BTree root;
    private final int n;
    private final int k;
    //private ConcurrentHashMap<Integer, BTree> nodes;
    private ConcurrentLinkedQueue<ArrayList<Integer>> results;

    public class BTree {
        ConcurrentHashMap<Integer, BTree> children;
        BTree (){this.children = new ConcurrentHashMap<>();}
    }

    public ParallelBTC(int n, int k){
        this.n = n;
        this.k = k;
        //nodes = new ConcurrentHashMap<>();
        root = new BTree();
        results = new ConcurrentLinkedQueue<>();
        root.children.put(0, new BTree());
        //nodes.put(0, root);
        add(n - 1);
    }

    public ConcurrentLinkedQueue<ArrayList<Integer>> getKIndices(){
        targetedDLS(this.k);
        return results;
    }

    public Set<List<Integer>> getKIndicesAsSet(){
        targetedDLS(this.k);
        return results.stream().collect(Collectors.toSet());
    }

    private void add(int n){
        for(int i = 1; i <= n; i++){
            if (root.children.containsKey(i))
                continue;
            root.children.put(i, new BTree());
            BTree node = root.children.get(i);
            //nodes.put(i, node);
            for (int j = 0; j < i; j++){
                node.children.put(j, root.children.get(j));
            }
        }
    }

    //dls - depth limited search
    private void targetedDLS(int limit){
        IntStream.rangeClosed(limit-1, n-1)
                .parallel()
                .forEach(i -> {
                    BTree node = root.children.get(i);
                    ArrayList<Integer> path = new ArrayList<>();
                    path.add(i);
                    targetedDLS(node, path, limit-1);
                });
    }

    private void targetedDLS(BTree node, ArrayList<Integer> path, int limit){
        if (limit == 0) {
            results.add(path);
            return;
        }
        for (Integer i : node.children.keySet()) {
            BTree child = node.children.get(i);
            ArrayList<Integer> pathC = new ArrayList<>(path);
            pathC.add(0,i);
            targetedDLS(child, pathC, limit - 1);
        }
    }

}
