package sample;

import jdk.nashorn.internal.ir.IfNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 6-10-2015.
 */
public class MockEffectenBeurs implements IEfficientenBeurs{
    ArrayList<IFonds> koersen;
    @Override
    public List<IFonds> getKoersen() {
        return koersen;
    }
}
