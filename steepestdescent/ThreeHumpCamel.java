/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework;

import gradientdescent.ReflectedRealMin;

/**
 *
 * @author austi
 */
public class ThreeHumpCamel extends ReflectedRealMin {
    public double x;
    public double y;
    public double value(){
        return 2*Math.pow(x,2) - 1.05 * Math.pow(x, 4) + Math.pow(x, 6) / 6 + x*y + Math.pow(y,2);
    }
}
