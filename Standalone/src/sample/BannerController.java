package sample;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class BannerController {

    private AEXBanner banner;
    private IEffectenBeurs effectenbeurs;
    private Timer pollingTimer;

    public BannerController(AEXBanner banner) {

        this.banner = banner;
        this.effectenbeurs = new MockEffectenBeurs();

        // Start polling timer: update banner every two seconds
        pollingTimer = new Timer();
        // TODO
        //  Deze taak wordt elke twee seconden (2000 ms) uitgevoerd, nieuwe koersen worden gegenereerd en in de AEX-banner
        //  klasse wordt de text veranderd naar de 'actuele' koersinformatie.
        TimerTask tt = new TimerTask()
        {
            @Override
            public void run()
            {
                String koersInfo = "";
                effectenbeurs.genereerKoersen();
                DecimalFormat df = new DecimalFormat("###0.00");
                for (IFonds f : effectenbeurs.getKoersen()) {
                    koersInfo = koersInfo + f.getNaam() + " " + df.format(f.getKoers()) + " ";
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
}
