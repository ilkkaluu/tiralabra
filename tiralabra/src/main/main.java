package main;
import util.RPrim;
import util.RKruskal;

class Main {

    public static void main(String[] args) {
        //RPrim.suorita(20, 40);
        RKruskal generator = new RKruskal(40); //input the size here
        generator.uudestaan();
        generator.tulosta();
    }
}
