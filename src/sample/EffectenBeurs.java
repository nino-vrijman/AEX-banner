package sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 6-10-2015.
 */
public class EffectenBeurs implements IEffectenBeurs {
    ArrayList<IFonds> koersen;
    @Override
    public List<IFonds> getKoersen() {
        return koersen;
    }
}
