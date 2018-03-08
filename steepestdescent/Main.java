/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework;

import gradientdescent.GradientDescentMinimizer;
import gradientdescent.SampleMinimizer;

/**
 *
 * @author austi
 */
public class Main {
    public static void main(String [] args){
        ThreeHumpCamel problem = new ThreeHumpCamel();
        SampleMinimizer sampleMin = new SampleMinimizer();
        GradientDescentMinimizer gradMin = new GradientDescentMinimizer();
        double [] mins = new double[2];
        double [] maxs = new double[2];
        double [] dxs = new double[2];
        int ix = problem.getRealParameterIndex("x");
        int iy = problem.getRealParameterIndex("y");
        mins[ix] = -5;
        maxs[ix] = 5;
        dxs[ix] = .01;
        mins[iy] = -5;
        maxs[iy] = 5;
        dxs[iy] = .01;
        sampleMin.setBox(mins,dxs,maxs);
        sampleMin.setProblem(problem);
        sampleMin.min();
        System.out.println("x="+problem.x+" y="+problem.y);
        
        gradMin.setProblem(problem);
        gradMin.min();
        System.out.println("x="+problem.x+" y="+problem.y);
    }
}
