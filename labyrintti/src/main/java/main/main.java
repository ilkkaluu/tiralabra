package main;

import util.RPrim;
import util.RKruskal;

class Main {

    public static void main(String[] args) {
        long pAloitusaika = System.nanoTime();

        RPrim.suorita(100, 100);      //Primin labyrintin koko, säädä arvoja halutun kokoiseksi tasaneliöksi.

        long pLopetusaika = System.nanoTime();
        long pKokonaisaika = (pLopetusaika - pAloitusaika) / 1000000;

        System.out.println("");
        System.out.println("Primissä aikaa kului: " + pKokonaisaika + " ms");
        
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");

        long kAloitusaika = System.nanoTime();
        
        RKruskal kruskalLabyrintti = new RKruskal(100); //Kruskalin labyrintin koko, säädä labyrintin kokoa
        kruskalLabyrintti.uudestaan();
        kruskalLabyrintti.tulosta();
        
        long kLopetusaika = System.nanoTime();
        long kKokonaisaika = (kLopetusaika - kAloitusaika) / 1000000;
        System.out.println("Kruskalissa aikaa kului: " + kKokonaisaika + " ms");
    }
}
