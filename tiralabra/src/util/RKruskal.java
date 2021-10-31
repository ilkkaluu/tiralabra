package util;

public class RKruskal {

    private int indeksi = 5;
    private KPiste[] labData; //useful maze Data
    private String[][] labNakyma; //maze Visual (used in printing)

    private String seina = "#"; //should probably be constants, oh well
    private String kaytava = " ";

    private int[][] suunnat = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; //multiply directions by 2 to get adjacent cell, default version is for walls

    public RKruskal(int koko) //Size must be odd, will fix any evens.
    {
        if (koko % 2 == 0) {
            koko++;
            if (koko < 5) { //minimum size of 5 or 3x3 cells
                koko = 5;
            }
            //System.out.println(size);
        }
        indeksi = koko;
    }

    public void tulosta() {
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
        labNakyma[1 + (int) (Math.random() * ((indeksi - 1) / 2)) * 2][0] = "  "; //random positioning for an opening
        labNakyma[1 + (int) (Math.random() * ((indeksi - 1) / 2)) * 2][(indeksi - 1)] = "  "; //random positioning for an ending
    }

    private void tyhjenna() { //creates a new ungenerated maze
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
        labData = labyData; //this is for accessing cells that we can use for generation
        labNakyma = labyNakyma;
    }

    private void poistaSeina(int suunta[], int i[]) { //makes the maze visible/navigable
        int nI[] = {kiinnita(i[0] + suunta[0]), kiinnita(i[1] + suunta[1])};
        if (nI[0] != 0 && nI[0] != (indeksi - 1)) {
            if (nI[1] != 0 && nI[1] != (indeksi - 1)) {
                labNakyma[nI[0]][nI[1]] = kaytava;
            }
        }
    }

    private int[] getUusi(int soluSijainti[]) { //gets a new direction from an inputed position of a cell
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

    private void alistus(int yla, int ala) { //changes all cells with inferior seed to the master seed
        for (int i = 0; i < (labData.length); i++) {
            if (labData[i].getSolu() == ala) {
                labData[i].setSolu(yla);
            }
        }
    }

    private int jaljellaOlevat() { //returns the amount of unique seeds that still exist, can be considered if seedsIn() > 1 then algorithm not finished
        int solujaJaljella[] = new int[indeksi * indeksi];
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

    private int[] satunSolunSijainti() {
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

    private int getSolunIndex(int p[]) { //returns the index at which the cell is in the maze data array that has the position given
        int kohta = indeksi + 1;
        for (int i = 0; i < (labData.length); i++) {
            int nykSijainti[] = labData[i].getSijainti();
            if (nykSijainti[0] == p[0] && nykSijainti[1] == p[1]) {//x==x and y==y
                kohta = i;
            }
        }
        return kohta;
    }

    private int getSoluSijainnista(int p[]) {
        int soluIndex = getSolunIndex(p);
        return labData[soluIndex].getSolu();
    }

    private int kiinnita(int x) {
        int i = Math.max(0, Math.min((indeksi - 1), x)); //clamps between 0 and the "size" of the maze
        return i;
    }

    private int yhdista(int x, int y) { //outputs a unique int from 2 ints, useful for seed application for kruskal
        double yhdiste = (0.5) * (x + y) * (x + y + 1) + y;
        return (int) yhdiste; //cantor is the function name (thanks wikipedia)
    }
}
