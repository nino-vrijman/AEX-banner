package AEXServer;

import Shared.IEffectenBeurs;
import Shared.IFonds;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 6-10-2015.
 */
public class EffectenBeurs implements IEffectenBeurs  {
    ArrayList<IFonds> koersen;

    @Override
    public ArrayList<IFonds> getKoersen() {
        return koersen;
        //  Door dit naar return null te veranderen worden de koersgegevens niet dubbel weergegeven.
//        return null;
    }
}
