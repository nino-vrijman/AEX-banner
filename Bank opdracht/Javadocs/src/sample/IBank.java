package sample;

import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.List;

/**
 * Created by Kevin on 24-11-2015.
 */
public interface IBank {
    /**
     * Deze methode kan aangeroepen worden om een transactie te verwerken
     * Er zal eerst gekeken worden of er voldoende saldo op de rekening staat indien er geld af gehaald wordt van een rekening.
     * @param transactie
     * @return true als de transactie succesvol is verwerkt, false als er iets mis gegaan zoals onvoldoende saldo.
     * @exception NotFound wanneer de bankrekening niet bekend is bij de bank.
     */
    public boolean verwerkTransactie(Transactie transactie) throws NotFound;

    /**
     * De rekeningen die geregistreerd staan bij de bank kunnen opgevraagd worden
     * @return List<String>rekeningNummers alle rekeningnummers die geregistreerd staan bij de bank.
     */
    public List<String> getAllRekeningNummers();
}
