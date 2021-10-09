
package util;

import java.util.*;

public class RKruskal {
    private final int n;
    private final int m;
    
    private List<Integer> seinat = new ArrayList<>();
    private HashMap<Integer, List<Integer>> neliot = new HashMap<>();
    private HashMap<Integer, List<Integer>> aNeliot = new HashMap<>();
    
    public RKruskal(int n, int m) {
        this.n = n;
        this.m = m;
    }
    
    public List<Integer> seinanPaikka() {
        alustaNeliot();
        if (n < 2 || m < 2) {
            return new ArrayList<>();
        } else {
            
            List<Integer> eka = new ArrayList<>();
            for (int i : this.neliot.keySet()) {
                eka = this.neliot.get(i);
                break;
            }
            if (eka.size() >= ((this.n * this.m) / 500) + 1 + 2 * (this.n + this.m) && (this.m * this.n) / 500 > 0) {
                int kerta = new Random().nextInt((this.n * this.m) / 500) + 1 + (this.n + this.m);
                for (int i = 0; i < kerta; i++) {
                    eka.remove(new Random().nextInt(eka.size() - 1));
                }
            }
            return eka;
        }
    }
    
    public void alustaNeliot() {
        for (int i = 1; i < 2 * this.n * this.m - this.n - this.m + 1; i++) {
            this.seinat.add(i);
        }
        for (int i = 1; i < this.n * this.m + 1; i++) {
            this.neliot.put(i, new ArrayList<Integer>());
            this.aNeliot.put(i, new ArrayList<Integer>());
        }
        for (int i : this.seinat) {
            if (i < this.n * this.m - this.m + 1) {
                neliot.get(i + (i - 1) / (this.n - 1)).add(i);
                neliot.get(i + 1 + (i - 1) / (this.n - 1)).add(i);
                aNeliot.get(i + (i - 1) / (this.n - 1)).add(i);
                aNeliot.get(i + 1 + (i - 1) / (this.n - 1)).add(i);
            } else {
                neliot.get(i - (this.n * this.m) + this.m).add(i);
                neliot.get(i - (this.n * this.m) + this.n + this.m).add(i);
                aNeliot.get(i - (this.n * this.m) + this.m).add(i);
                aNeliot.get(i - (this.n * this.m) + this.n + this.m).add(i);
            }
        }
    }
    
    public HashMap<Integer, List<Integer>> getNeliot() {
        return this.aNeliot;
    }
    
    
}
