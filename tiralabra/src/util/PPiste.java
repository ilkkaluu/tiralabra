package util;

public class PPiste {

    Integer leveys;
    Integer korkeus;
    PPiste piste;

    public PPiste(int x, int y, PPiste p) {
        leveys = x;
        korkeus = y;
        piste = p;
    }

    public PPiste vastakkainen() {   //lasketaan vastakkaisen kohdan sijainti
        if (this.leveys.compareTo(piste.leveys) != 0) {
            return new PPiste(this.leveys + this.leveys.compareTo(piste.leveys), this.korkeus, this);
        }
        if (this.korkeus.compareTo(piste.korkeus) != 0) {
            return new PPiste(this.leveys, this.korkeus + this.korkeus.compareTo(piste.korkeus), this);
        }
        return null;
    }
}
