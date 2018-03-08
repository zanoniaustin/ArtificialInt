/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework;

import gradientdescent.ReflectedRealMin;
import java.lang.Math;
/**
 *
 * @author austi
 */
public class EggHolder extends ReflectedRealMin{
    public double x;
    public double y;
    public double value(){
        return (-1 * (y+47) * Math.sin(Math.sqrt(Math.abs(x/2+y+47))) - x * Math.sin(Math.sqrt(Math.abs(x-y+47))));
    }
}
