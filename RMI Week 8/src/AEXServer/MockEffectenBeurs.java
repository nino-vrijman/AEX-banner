package AEXServer;

import Shared.Fonds;
import Shared.IEffectenBeurs;
import Shared.IFonds;
import fontys.observer.BasicPublisher;
import fontys.observer.RemotePropertyListener;
import fontys.observer.RemotePublisher;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Nino Vrijman on 10-10-2015.
 */
public class MockEffectenBeurs extends UnicastRemoteObject implements IEffectenBeurs, RemotePublisher
{
    ArrayList<IFonds> koersen;
    //Week 7 Registry
    private Registry registry = null;
    //week 8 BasicPublisher
    BasicPublisher publisher ;

    public MockEffectenBeurs() throws RemoteException {
        koersen = new ArrayList<>();
        koersen.add(new Fonds("Unilever", 91.88));
        koersen.add(new Fonds("Shell", 7.81));
        publisher = new BasicPublisher(new String[] { "beurs" });
    }

    @Override
    public ArrayList<IFonds> getKoersen()
    {
        return koersen;
    }

    public void genereerKoersen() {
        Random r = new Random();
        for (IFonds f : koersen) {
            double nieuweKoers = f.getKoers() * (0.95 + (1.05 - 0.95) * r.nextDouble());
            f.setKoers(nieuweKoers);
        }
        publisher.inform(this, "beurs", null, this);
    }

    @Override
    public void addListener(RemotePropertyListener remotePropertyListener, String s) throws RemoteException {
        publisher.addListener(remotePropertyListener, s);
    }

    @Override
    public void removeListener(RemotePropertyListener remotePropertyListener, String s) throws RemoteException {
        publisher.removeListener(remotePropertyListener,s);
    }
}
