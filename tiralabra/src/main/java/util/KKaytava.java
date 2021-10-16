
package util;

import tiralabra.KSolu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static tiralabra.KSolu.Type.KAYTAVA;

public class KKaytava {

    private int korkeus;
    private int leveys;

    public KKaytava(int korkeus, int leveys) {
        this.korkeus = (korkeus - 1) / 2;
        this.leveys = (leveys - 1) / 2;
    }

    public List<KSolu> generoi() {
        var reunat = luoReunat();
        Collections.shuffle(reunat);
        var tree = luoRST(reunat);
        return luoKaytavat(tree);
    }

    private List<KReuna> luoReunat() {
        var reunat = new ArrayList<KReuna>();
        for (int sarake = 1; sarake < leveys; sarake++) {
            reunat.add(new KReuna(indeksiin(0, sarake),
                               indeksiin(0, sarake - 1)));
        }
        for (int rivi = 1; rivi < korkeus; rivi++) {
            reunat.add(new KReuna(indeksiin(rivi, 0),
                               indeksiin(rivi - 1, 0)));
        }
        for (int rivi = 1; rivi < korkeus; rivi++) {
            for (int pilari = 1; pilari < leveys; pilari++) {
                reunat.add(new KReuna(indeksiin(rivi, pilari),
                                   indeksiin(rivi, pilari - 1)));
                reunat.add(new KReuna(indeksiin(rivi, pilari),
                                   indeksiin(rivi - 1, pilari)));
            }
        }
        return reunat;
    }

    private int indeksiin(int rivi, int pilari) {
        return rivi * leveys + pilari;
    }

    private List<KReuna> luoRST(List<KReuna> edges) {
        var keJoukko = new KEJoukko(leveys * korkeus);
        return edges
            .stream()
            .filter(edge -> connects(edge, keJoukko))
            .collect(toList());
    }

    private boolean connects(KReuna edge, KEJoukko keJoukko) {
        return keJoukko.yhdiste(edge.getEkaSolu(), edge.getTokaSolu());
    }

    private List<KSolu> luoKaytavat(List<KReuna> vPuu) {
        return vPuu
            .stream()
            .map(reuna -> {
                var eka = indeksista(reuna.getEkaSolu());
                var toka = indeksista(reuna.getTokaSolu());
                return getKaytava(eka, toka);
            }).collect(toList());
    }

    private KSolu indeksista(int index) {
        var row = index / leveys;
        var column = index % leveys;
        return new KSolu(row, column, KAYTAVA);
    }

    private KSolu getKaytava(KSolu eka, KSolu toka) {
        var rivi = eka.getRivi() + toka.getRivi() + 1;
        var pilari = eka.getPilari() + toka.getPilari() + 1;
        return new KSolu(rivi, pilari, KAYTAVA);
    }
}