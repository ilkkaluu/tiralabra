package util;


import java.lang.Math;

public class RKruskal {

    private int indeksi = 5;
    private KPiste[] labData;
    private String[][] labNakyma;

    private String seina = "#"; 
    private String kaytava = " ";

    private int[][] suunnat = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; 

    public RKruskal(int koko)
    {
        if (koko % 2 == 0) {
            koko++;
            if (koko < 5) { 
                koko = 5;
            }
        }
        indeksi = koko;
    }

    public void tulosta() {                             //Tulostaa labyrintin näkymän
        System.out.print("\n");
        for (int x = 0; x < (indeksi); x++) {
            for (int y = 0; y < (indeksi); y++) {
                System.out.print(labNakyma[x][y]);
            }
            System.out.print("\n");
        }
    }

    public void uudestaan() {
        tyhjenna();
        while (jaljellaOlevat() > 1) {
            int uusiSijainti[] = satunSolunSijainti();
            int uusiSuunta[] = getUusi(uusiSijainti);
            if (uusiSuunta[0] != -1) {
                poistaSeina(uusiSuunta, uusiSijainti);
                int aSij[] = {uusiSijainti[0] + (uusiSuunta[0] * 2), uusiSijainti[1] + (uusiSuunta[1] * 2)};
                int solu1 = getSoluSijainnista(uusiSijainti);
                int solu2 = getSoluSijainnista(aSij);
                alistus(solu1, solu2);
            }
        }
        labNakyma[1 + (int) (Math.random() * ((indeksi - 1) / 2)) * 2][0] = "  ";             //Luodaan sisäänkäynnin sijainti
        labNakyma[1 + (int) (Math.random() * ((indeksi - 1) / 2)) * 2][(indeksi - 1)] = "  "; //Luodaan uloskäynnin sijainti
    }

    private void tyhjenna() {                                                                //Luo tyhjän labyrintin
        KPiste labyData[] = new KPiste[((indeksi - 1) / 2) * ((indeksi - 1) / 2)];
        String labyNakyma[][] = new String[indeksi][indeksi];
        int index = 0;
        for (int x = 0; x < (indeksi); x++) {
            for (int y = 0; y < (indeksi); y++) {
                int p[] = {x, y};
                if ((x + 1) % 2 == 0 && (y + 1) % 2 == 0) {
                    labyNakyma[x][y] = kaytava;
                    labyData[index] = new KPiste(yhdista(x, y), p);
                    index++;
                } else {
                    labyNakyma[x][y] = seina;
                }
            }
        }
        labData = labyData; 
        labNakyma = labyNakyma;
    }

    private void poistaSeina(int suunta[], int i[]) {                           //Poistaa seinän kahden solun väliltä
        int nI[] = {kiinnita(i[0] + suunta[0]), kiinnita(i[1] + suunta[1])};
        if (nI[0] != 0 && nI[0] != (indeksi - 1)) {
            if (nI[1] != 0 && nI[1] != (indeksi - 1)) {
                labNakyma[nI[0]][nI[1]] = kaytava;
            }
        }
    }

    private int[] getUusi(int soluSijainti[]) {                                 //Valitsee uuden menosuunnan valitusta solusta
        int mahdSuunnat[][] = new int[4][2];
        int virhe[] = {-1, -1};
        int solu = labData[getSolunIndex(soluSijainti)].getSolu();
        int index = 0;
        boolean tarkista = false;
        for (int i = 0; i < 4; i++) {
            int uusiSuunta[] = {kiinnita(soluSijainti[0] + suunnat[i][0]), kiinnita(soluSijainti[1] + suunnat[i][1])};
            if (uusiSuunta[0] != 0 && uusiSuunta[0] != (indeksi - 1)) {
                if (uusiSuunta[1] != 0 && uusiSuunta[1] != (indeksi - 1)) {
                    tarkista = true;
                }
            }
            int uusiKiinnite[] = {kiinnita(soluSijainti[0] + (suunnat[i][0] * 2)), kiinnita(soluSijainti[1] + (suunnat[i][1] * 2))};
            if (tarkista && uusiKiinnite[0] != 0 && uusiKiinnite[0] != (indeksi - 1)) {
                if (getSoluSijainnista(uusiKiinnite) != solu && uusiKiinnite[1] != 0 && uusiKiinnite[1] != (indeksi - 1)) {
                    mahdSuunnat[index][0] = suunnat[i][0];
                    mahdSuunnat[index++][1] = suunnat[i][1];
                }
            }
        }
        if (index == 0) {
            labData[getSolunIndex(soluSijainti)].kayttoTrue = false;
            return virhe;
        }
        return mahdSuunnat[(int) (Math.random() * index)];
    }

    private void alistus(int yla, int ala) {                                    //Alistaa kaikki alasolut yhdelle yläsolulle
        for (int i = 0; i < (labData.length); i++) {
            if (labData[i].getSolu() == ala) {
                labData[i].setSolu(yla);
            }
        }
    }

    private int jaljellaOlevat() {                                              //Palauttaa jäljellä olevien solujen määrän. 
        int solujaJaljella[] = new int[indeksi * indeksi];                      //Ohjelma suorittaa kunnes määrä on 1
        int index = 0;
        for (int i = 0; i < labData.length; i++) {
            int nykSolu = labData[i].getSolu();
            boolean lisays = true;
            for (int j = 0; j < solujaJaljella.length; j++) {
                if (solujaJaljella[j] == nykSolu) {
                    lisays = false;
                    break;
                }
            }
            if (lisays) {
                solujaJaljella[index++] = nykSolu;
            }
        }
        return index;
    }

    private int[] satunSolunSijainti() {                                        //Valitsee uuden satunnaisen solun sijainnin
        int xKohta = 1 + (int) (Math.random() * ((indeksi - 1) / 2)) * 2;
        int yKohta = 1 + (int) (Math.random() * ((indeksi - 1) / 2)) * 2;
        int sijainti[] = {xKohta, yKohta};
        int soluIndex = getSolunIndex(sijainti);
        if (labData[soluIndex].kayttoTrue != true) {
            for (int i = 0; i < labData.length; i++) {
                if (labData[i].kayttoTrue = true) {
                    sijainti[0] = labData[0].getSijainti()[0];
                    sijainti[1] = labData[1].getSijainti()[1];
                    break;
                }
            }
        }
        if (getUusi(sijainti)[0] == -1) {
            return satunSolunSijainti();
        }
        return sijainti;
    }

    private int getSolunIndex(int p[]) {                                        //Palauttaa valitun solun indeksin
        int kohta = indeksi + 1;
        for (int i = 0; i < (labData.length); i++) {
            int nykSijainti[] = labData[i].getSijainti();
            if (nykSijainti[0] == p[0] && nykSijainti[1] == p[1]) {
                kohta = i;
            }
        }
        return kohta;
    }

    private int getSoluSijainnista(int p[]) {                                   //Palauttaa solun valitusta sijainnista
        int soluIndex = getSolunIndex(p);
        return labData[soluIndex].getSolu();
    }

    private int kiinnita(int x) {
        int i = Math.max(0, Math.min((indeksi - 1), x));                        //Apumetodi kiinnitykseen, kiinnitys nollan ja labyrintin koon välillä
        return i;
    }

    private int yhdista(int x, int y) {                                         //Tulostaa uniikin yhdisteindeksin kahdesta indeksistä
        double yhdiste = (0.5) * (x + y) * (x + y + 1) + y;
        return (int) yhdiste;
    }
}