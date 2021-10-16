
package util;

import static java.util.stream.IntStream.range;

public class KEJoukko {

    private int[] vanhempi;

    private int[] sija;

    private int koko;

    public KEJoukko(int koko) {
        this.koko = koko;
        vanhempi = new int[koko];
        sija = new int[koko];
        range(0, koko).forEach(this::luoJoukko);
    }

    private void luoJoukko(int i) {
        vanhempi[i] = i;
        sija[i] = 0;
    }

    public int getKoko() {
        return koko;
    }

    public int loyda(int i) {
        if (i != vanhempi[i])
            vanhempi[i] = loyda(vanhempi[i]);
        return vanhempi[i];
    }

    public boolean yhdiste(int i, int j) {
        var iJuuri = loyda(i);
        var jJuuri = loyda(j);
        if (iJuuri == jJuuri)
            return false;
        if (sija[iJuuri] < sija[jJuuri]) {
            vanhempi[iJuuri] = jJuuri;
        } else {
            vanhempi[jJuuri] = iJuuri;
            if (sija[iJuuri] == sija[jJuuri])
                sija[iJuuri]++;
        }
        koko--;
        return true;
    }
}