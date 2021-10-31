package util;

import java.util.ArrayList;

public class RPrim {

    public static void suorita(int pLeveys, int pKorkeus) {

        int leveys = pLeveys, korkeus = pKorkeus;   //labyrintin mitat

        StringBuilder stringBuilder = new StringBuilder(korkeus);   //labyrintin seinät rakennetaan
        for (int x = 0; x < korkeus; x++) {
            for (int y = 0; y < leveys; y++) {
                stringBuilder.append('#');

            }
        }
        char[][] labyrintti = new char[leveys][korkeus];
        for (int x = 0; x < leveys; x++) {
            labyrintti[x] = stringBuilder.toString().toCharArray();
        }

        Piste alku = new Piste((int) (Math.random() * leveys), (int) (Math.random() * korkeus), null);
        labyrintti[alku.leveys][alku.korkeus] = '#';       //valitaan aloituskohta

        ArrayList< Piste> koskettavat = new ArrayList<Piste>();     //käydään aloituskohdan naapurit läpi
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0 || x != 0 && y != 0) {
                    continue;
                }
                try {
                    if (labyrintti[alku.leveys + x][alku.korkeus + y] == ' ') {
                        continue;
                    }
                } catch (Exception e) {
                    continue;
                }

                koskettavat.add(new Piste(alku.leveys + x, alku.korkeus + y, alku));    //valitaan sopivat naapurit
            }
        }

        Piste vika = null;
        while (!koskettavat.isEmpty()) {

            Piste nykyinen = koskettavat.remove((int) (Math.random() * koskettavat.size()));    //valitaan nykyinen kohta sattumalla
            Piste vastakohta = nykyinen.vastakkainen();
            try {
                if (labyrintti[nykyinen.leveys][nykyinen.korkeus] == '#') {        //jos nykyinen kohta labyrintissa ja sen vastakohta ovat seiniä
                    if (labyrintti[vastakohta.leveys][vastakohta.korkeus] == '#') {
                        labyrintti[nykyinen.leveys][nykyinen.korkeus] = ' ';       //avataan väylä kohtien välille
                        labyrintti[vastakohta.leveys][vastakohta.korkeus] = ' ';
                        vika = vastakohta;      //merkitään viimeinen kohta

                        for (int x = -1; x <= 1; x++) {     //käydään kohdan naapurit läpi
                            for (int y = -1; y <= 1; y++) {
                                if (x == 0 && y == 0 || x != 0 && y != 0) {
                                    continue;
                                }
                                try {
                                    if (labyrintti[vastakohta.leveys + x][vastakohta.korkeus + y] == ' ') {
                                        continue;
                                    }
                                } catch (Exception e) {
                                    continue;
                                }
                                koskettavat.add(new Piste(vastakohta.leveys + x, vastakohta.korkeus + y, vastakohta));
                            }
                        }
                    }
                }
            } catch (Exception e) {
            }
            if (koskettavat.isEmpty()) {        //merkitään lopetuskohta, mikäli suoritus päättynyt
                labyrintti[vika.leveys][vika.korkeus] = '#';
            }
        }

        for (int x = 0; x < leveys; x++) {      //tulostus
            for (int y = 0; y < korkeus; y++) {
                System.out.print(labyrintti[x][y]);
            }
            System.out.println();
        }
    }

    static class Piste {

        Integer leveys;
        Integer korkeus;
        Piste piste;

        public Piste(int x, int y, Piste p) {
            leveys = x;
            korkeus = y;
            piste = p;
        }

        public Piste vastakkainen() {   //lasketaan vastakkaisen kohdan sijainti
            if (this.leveys.compareTo(piste.leveys) != 0) {
                return new Piste(this.leveys + this.leveys.compareTo(piste.leveys), this.korkeus, this);
            }
            if (this.korkeus.compareTo(piste.korkeus) != 0) {
                return new Piste(this.leveys, this.korkeus + this.korkeus.compareTo(piste.korkeus), this);
            }
            return null;
        }
    }
}
