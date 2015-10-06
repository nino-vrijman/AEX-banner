package sample;

/**
 * Created by Kevin on 6-10-2015.
 */
public class Fonds implements IFonds{
    String naam;
    Double koers;
    @Override
    public String getNaam() {
        return naam;
    }

    @Override
    public double getKoers() {
        return koers;
    }
}
