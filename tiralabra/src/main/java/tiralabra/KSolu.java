package tiralabra;


import java.util.Objects;

public class KSolu {

    public enum Type {
        KAYTAVA,
        SEINA,
        EXIT;
    }

    private final int rivi;

    private final int pilari;

    private final Type tyyppi;

    public KSolu(int rivi, int pilari, Type tyyppi) {
        this.rivi = rivi;
        this.pilari = pilari;
        this.tyyppi = tyyppi;
    }

    public int getRivi() {
        return rivi;
    }

    public int getPilari() {
        return pilari;
    }

    public boolean onKaytava() {
        return tyyppi == Type.KAYTAVA;
    }

    public boolean onSeina() {
        return tyyppi == Type.SEINA;
    }

    public boolean onExit() {
        return tyyppi == Type.EXIT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var cell = (KSolu) o;
        return rivi == cell.rivi &&
            pilari == cell.pilari &&
            tyyppi == cell.tyyppi;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rivi, pilari, tyyppi);
    }

    @Override
    public String toString() {
        return "Solu{" +
            "row=" + rivi +
            ", column=" + pilari +
            ", type=" + tyyppi +
            '}';
    }
}