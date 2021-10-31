package util;

import java.util.Objects;

public class KPiste {

    private int solu;
    private int sijainti[] = new int[2];
    public boolean kayttoTrue;

    public KPiste(int s, int p[]) {
        solu = s; //Seed Instanciation for kruskal algorithm, can use this.seed for easier readability but oh well
        sijainti[0] = p[0];  //Position Instanciation
        sijainti[1] = p[1];  //For easier getting
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