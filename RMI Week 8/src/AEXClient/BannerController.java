package AEXClient;

import AEXServer.MockEffectenBeurs;
import Shared.IEffectenBeurs;
import Shared.IFonds;
import fontys.observer.RemotePublisher;

import java.beans.PropertyChangeEvent;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class BannerController extends UnicastRemoteObject implements IBanner {

    private AEXBanner banner;
    private Timer pollingTimer;
    //week 7 registry
    private Registry registry = null;
    private RemotePublisher link = null;
   // private IEffectenBeurs effectenbeurs = null;
    private List<IFonds> fondsen = null;

    public BannerController(AEXBanner banner) throws RemoteException {

        this.banner = banner;
        //this.effectenbeurs = new MockEffectenBeurs();

        // Start polling timer: update banner every two seconds
        pollingTimer = new Timer();
//        Host();

        // opdracht week 7
        Client();

        // TODO
        //  Deze taak wordt elke twee seconden (2000 ms) uitgevoerd, nieuwe koersen worden gegenereerd en in de AEX-banner
        //  klasse wordt de text veranderd naar de 'actuele' koersinformatie.
        TimerTask tt = new TimerTask()
        {
            @Override
            public void run()
            {

            }
        };
    }

    // Stop banner controller
    public void stop() {
        pollingTimer.cancel();
        // Stop simulation timer of effectenbeurs
        // TODO
        try {
            link.removeListener(this, "beurs");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        banner.stop();
    }
    public void updateBanner()
    {
        String koersInfo = "";
        DecimalFormat df = new DecimalFormat("###0.00");
            if (fondsen != null){
                for (IFonds f : fondsen) {
                    koersInfo = koersInfo + f.getNaam() + " " + df.format(f.getKoers()) + " ";
                }
            }
        banner.setKoersen(koersInfo);
    }
    public void Client()
    {
        try{
            registry = LocateRegistry.getRegistry("localhost", 1099);
        }
        catch (RemoteException ex)
        {
            System.out.println(ex);
        }

        if(registry != null)
        {
            try{
                link = (RemotePublisher) registry.lookup("AEXServer");
                link.addListener(this, "beurs");
            }
            catch (NotBoundException ex)
            {
                System.out.println(ex);
            }
            catch ( RemoteException ef)
            {
                System.out.println(ef);
            }
        }

    }
//    public void Host()
//    {
//        try {
//            registry = LocateRegistry.createRegistry(1098);
//        }
//        catch (RemoteException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            registry.rebind("bannerController", this);
//        } catch (AccessException e) {
//            e.printStackTrace();
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) throws RemoteException {
        fondsen = ((IEffectenBeurs) propertyChangeEvent.getNewValue()).getKoersen();
        updateBanner();
     }
}
