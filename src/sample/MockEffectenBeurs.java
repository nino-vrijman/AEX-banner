package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Nino Vrijman on 10-10-2015.
 */
public class MockEffectenBeurs implements IEffectenBeurs
{
    ArrayList<IFonds> koersen;

    public MockEffectenBeurs() {
        koersen = new ArrayList<>();
        koersen.add(new Fonds("Unilever", 91.88));
        koersen.add(new Fonds("Shell", 7.81));
    }

    @Override
    public List<IFonds> getKoersen()
    {
        return koersen;
    }

    public void genereerKoersen() {
        Random r = new Random();
        for (IFonds f : koersen) {
            double nieuweKoers = f.getKoers() * (0.95 + (1.05 - 0.95) * r.nextDouble());
            f.setKoers(nieuweKoers);
        }
    }
}
