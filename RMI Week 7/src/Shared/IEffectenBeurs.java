package Shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 6-10-2015.
 */
public interface IEffectenBeurs extends Remote {
    ArrayList<IFonds> getKoersen() throws RemoteException;
}
