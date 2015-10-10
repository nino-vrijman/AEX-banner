package sample;

/**
 * Created by Kevin on 6-10-2015.
 */
public class Fonds implements IFonds{
    String naam;
    Double koers;

    public Fonds (String naam, Double koers) {
        this.naam = naam;
        this.koers = koers;
    }

    @Override
    public String getNaam() {
        return naam;
    }

    @Override
    public double getKoers() {
        return koers;
    }

    @Override
    public void setKoers(Double nieuweKoers) {
        this.koers = nieuweKoers;
    }
}
