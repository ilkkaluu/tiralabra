<<<<<<< HEAD:tiralabra/src/util/RPrim.java
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

        PPiste alku = new PPiste((int) (Math.random() * leveys), (int) (Math.random() * korkeus), null);
        labyrintti[alku.leveys][alku.korkeus] = '#';       //valitaan aloituskohta

        ArrayList<PPiste> koskettavat = new ArrayList<PPiste>();     //käydään aloituskohdan naapurit läpi
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

                koskettavat.add(new PPiste(alku.leveys + x, alku.korkeus + y, alku));    //valitaan sopivat naapurit
            }
        }

        PPiste vika = null;
        while (!koskettavat.isEmpty()) {

            PPiste nykyinen = koskettavat.remove((int) (Math.random() * koskettavat.size()));    //valitaan nykyinen kohta sattumalla
            PPiste vastakohta = nykyinen.vastakkainen();
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
                                koskettavat.add(new PPiste(vastakohta.leveys + x, vastakohta.korkeus + y, vastakohta));
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

}
=======
package util;

import util.PPiste;
import java.util.*;

public class RPrim {

    public static void suorita(int pLeveys, int pKorkeus) {

        int leveys = pLeveys;
        int korkeus = pKorkeus;                                    //labyrintin mitat

        StringBuilder stringBuilder = new StringBuilder(korkeus);  //labyrintin seinät rakennetaan
        for (int x = 0; x < korkeus; x++) {
            for (int y = 0; y < leveys; y++) {
                stringBuilder.append('#');

            }
        }
        char[][] labyrintti = new char[leveys][korkeus];
        for (int x = 0; x < leveys; x++) {
            labyrintti[x] = stringBuilder.toString().toCharArray();
        }

        PPiste alku = new PPiste((int) (Math.random() * leveys), (int) (Math.random() * korkeus), null);
        labyrintti[alku.leveys][alku.korkeus] = '#';                            //valitaan aloituskohta

        ArrayList<PPiste> koskettavat = new ArrayList<PPiste>();                //käydään aloituskohdan naapurit läpi
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

                koskettavat.add(new PPiste(alku.leveys + x, alku.korkeus + y, alku));            //valitaan sopivat naapurit
            }
        }

        PPiste vika = null;
        while (!koskettavat.isEmpty()) {

            PPiste nykyinen = koskettavat.remove((int) (Math.random() * koskettavat.size()));    //valitaan nykyinen kohta sattumalla
            PPiste vastakohta = nykyinen.vastakkainen();
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
                                koskettavat.add(new PPiste(vastakohta.leveys + x, vastakohta.korkeus + y, vastakohta));
                            }
                        }
                    }
                }
            } catch (Exception e) {
            }
            if (koskettavat.isEmpty()) {                                        //merkitään lopetuskohta, mikäli suoritus päättynyt
                labyrintti[vika.leveys][vika.korkeus] = '#';
            }
        }

        for (int x = 0; x < leveys; x++) {                                      //tulostus
            for (int y = 0; y < korkeus; y++) {
                System.out.print(labyrintti[x][y]);
            }
            System.out.println();
        }
    }
}
>>>>>>> 92587345ab0b8050e7754639a93cce59bf1575b1:labyrintti/src/main/java/util/RPrim.java
