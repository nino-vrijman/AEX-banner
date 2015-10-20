package AEXClient;

import AEXServer.MockEffectenBeurs;
import Shared.IEffectenBeurs;
import Shared.IFonds;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class BannerController {

    private AEXBanner banner;
    private Timer pollingTimer;
    //week 7 registry
    private Registry registry = null;
    private IEffectenBeurs effectenbeurs = null;

    public BannerController(AEXBanner banner) throws RemoteException {

        this.banner = banner;
        //this.effectenbeurs = new MockEffectenBeurs();

        // Start polling timer: update banner every two seconds
        pollingTimer = new Timer();

        Client();
        // TODO
        //  Deze taak wordt elke twee seconden (2000 ms) uitgevoerd, nieuwe koersen worden gegenereerd en in de AEX-banner
        //  klasse wordt de text veranderd naar de 'actuele' koersinformatie.
        TimerTask tt = new TimerTask()
        {
            @Override
            public void run()
            {
                String koersInfo = "";
                DecimalFormat df = new DecimalFormat("###0.00");
                try {
                    for (IFonds f : effectenbeurs.getKoersen()) {
                        koersInfo = koersInfo + f.getNaam() + " " + df.format(f.getKoers()) + " ";
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                banner.setKoersen(koersInfo);
            }
        };

        pollingTimer.scheduleAtFixedRate(tt, 2000, 2000);
    }

    // Stop banner controller
    public void stop() {
        pollingTimer.cancel();
        // Stop simulation timer of effectenbeurs
        // TODO
        banner.stop();
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
                effectenbeurs = (IEffectenBeurs) registry.lookup("AEXServer");
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
}
