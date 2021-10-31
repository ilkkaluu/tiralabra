package util;

import java.util.Objects;

public class KPiste {

    private int solu;
    private int sijainti[] = new int[2];
    public boolean kayttoTrue;

    public KPiste(int s, int p[]) {
        solu = s; 
        sijainti[0] = p[0];  
        sijainti[1] = p[1];
        kayttoTrue = true;
    }

    public int getSolu() {
        return solu;
    }

    public int[] getSijainti() {
        return sijainti;
    }

    public void setSolu(int s) {
        solu = s;
    }
}