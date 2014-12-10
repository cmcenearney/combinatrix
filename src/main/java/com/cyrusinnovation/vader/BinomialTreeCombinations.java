package com.cyrusinnovation.vader;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.util.*;

public class BinomialTreeCombinations {

    private final BTree root;
    private final int n;

    public class BTree {
        HashMap<Integer, BTree> children;
        BTree (){this.children = new HashMap<>();}
    }

    public BinomialTreeCombinations(int n){
        this.n = n;
        root = new BTree();
        root.children.put(0, new BTree());
        add(n - 1);
    }

    public Set<List<Integer>> getKIndices(int k){
        return dls(k);
    }

    public Set<List<Integer>> getAllPaths(){
        return dfs(root);
    }

    public static long nkPerms(int n,int k){
        return CombinatoricsUtils.factorial(n) / (CombinatoricsUtils.factorial(k) * CombinatoricsUtils.factorial(n-k));
    }

    private void add(int n){
        for(int i = 1; i <= n; i++){
            if (root.children.containsKey(i))
                continue;
            root.children.put(i, new BTree());
            BTree node = root.children.get(i);
            for (int j = 0; j < i; j++){
                node.children.put(j, root.children.get(j));
            }
        }
    }

    private Set<List<Integer>> dfs(BTree node){
        Set<List<Integer>> results = new HashSet<>();
        List<Integer> path = new ArrayList<>();
        Set<BTree> visited = new HashSet<>();
        dfs(node, path, results, visited);
        return results;
    }

    private void  dfs(BTree node, List<Integer> path, Set<List<Integer>> results, Set<BTree> visited){
        visited.add(node);
        if (node.children.isEmpty()) {
            results.add(path);
            return;
        }
        for (Integer i : node.children.keySet()) {
            BTree child = node.children.get(i);
            ArrayList<Integer> pathC = new ArrayList<>(path);
            pathC.add(i);
            dfs(child, pathC, results, visited);
        }
    }

    private Set<List<Integer>> dls(int limit){
        Set<List<Integer>> results = new HashSet<>();
        List<Integer> path = new ArrayList<>();
        Set<BTree> visited = new HashSet<>();
        dls(root, path, results, visited, limit);
        return results;
    }

    private void  dls(BTree node, List<Integer> path, Set<List<Integer>> results, Set<BTree> visited, int limit){
        visited.add(node);
        if (limit == 0) {
            results.add(path);
            return;
        }
        for (Integer i : node.children.keySet()) {
            BTree child = node.children.get(i);
            ArrayList<Integer> pathC = new ArrayList<>(path);
            pathC.add(0,i);
            dls(child, pathC, results, visited, limit - 1);
        }
    }

}
