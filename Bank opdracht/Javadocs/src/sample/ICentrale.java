package sample;

import org.omg.CosNaming.NamingContextPackage.NotFound;

/**
 * Created by Kevin on 24-11-2015.
 */
public interface ICentrale {

    /**
     * verwerkTransactie zal na kijken bij welke banken de rekeningen horen en stuurt de transactie door naar de juiste bank. Deze zal ze vervolgens verwerken.
     * @param transactie
     * @return true als de transactie succesvol is verwerkt, false als er iets mis gegaan zoals onvoldoende saldo.
     * @throws NotFound
     */
    public boolean verwerkTransactie(Transactie transactie) throws NotFound;

    /**
     * Een bank kan zich aanmelden bij de overboekcentrale door zichzelf mee te geven
     * @param bank de bank die zich wilt aanemelden
     * @return true als het gelukt is om zich aan te melden bij de centrale, false als het niet mogelijk is om zich aan te melden
     * @throws AlAangemeld als de bank al bekend staat bij de overboekcentrale.
     */
    public boolean bankAanmelden (Bank bank) throws AlAangemeld;

    /**
     * Een bank kan zich aanmelden bij de overboek centrale door zichzelf mee te geven
     * @param bank de bank die zich wilt afmelden
     * @return true als het gelukt is om zich af te melden, false als het niet mogelijk is om zich af te melden door bijvoorbeeld nog openstaande transacties
     * @throws NietAangemeld als de bank niet is aangemeld bij de overboek centrale.
     */
    public boolean bankAfmelden(Bank bank) throws NietAangemeld;

}
