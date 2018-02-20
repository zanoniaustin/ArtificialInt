/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steepestdescent;

import java.util.HashMap;
import static java.lang.Math.*;

/**
 *
 * @author warren
 */
public class Rosenbrock implements RealMin {
        double[] values = new double[2];
    private static final String[] names = new String[] { "x", "y" };
    private static final HashMap < String, Integer > indexes = new HashMap < String, Integer > ();
    static {
        for (int i=0; i<names.length; ++i) {
            indexes.put(names[i],i);
        }
    }
    public static final int IX = indexes.get("x");
    public static final int IY = indexes.get("y");

    @Override
    public int getRealParameterSize() {
        return names.length;
    }

    @Override
    public String getRealParameterName(int i) { return names[i]; }

    @Override
    public int getRealParameterIndex(String name) { return indexes.get(name); }
    

    @Override
    public double getRealParameterValue(int index) {
        return values[index];
    }

    @Override
    public void setRealParameterValue(int index, double value) {
        values[index] = value;
    }
    public Rosenbrock() {
    }

    public Rosenbrock(Rosenbrock copy) {
        System.arraycopy(copy.values, 0, copy.values, 0, values.length);
    }

    @Override
    public RealMin copy() {
        return new Rosenbrock(this);
    }

    @Override
    public double getValue() {
        double x = values[IX]-1;
        double y = values[IY];
        return pow(1-x,2)+100*pow((y-pow(x,2)),2);
    }
}
