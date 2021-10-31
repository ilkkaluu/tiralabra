
package tiralabra;

import util.RKruskal;
import util.RPrim; 

public class Main {


    public static void main(String[] args) {
        long aloitus = System.nanoTime();
        RPrim.suorita(50, 50);
        
        //RKruskal kruskal = new RKruskal(100);
        //kruskal.uudestaan();
        //kruskal.tulosta();
        long lopetus = System.nanoTime();
        long kokonaisaika = lopetus - aloitus;
        kokonaisaika = kokonaisaika / 1000000;
        System.out.println("Aikaa kului: " + kokonaisaika + " ms");
    }
    
}
