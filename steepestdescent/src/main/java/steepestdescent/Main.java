/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steepestdescent;

/**
 *
 * @author wmacevoy
 */
public class Main {

    public static void main(String[] args) {
        RealMin problem = new Rosenbrock();
        int dim = problem.getRealParameterSize();

        SteepestDescentMinimizer minimizer = new SteepestDescentMinimizer();
        minimizer.setProblem(problem);

        for (int trial = 0; trial < 1000; ++trial) {
            for (int i = 0; i < dim; ++i) {
                problem.setRealParameterValue(i, 0);
            }
            minimizer.min();
        }

        for (int i = 0; i < problem.getRealParameterSize(); ++i) {
            String name = problem.getRealParameterName(i);
            double value = problem.getRealParameterValue(i);
            System.out.println(name + "=" + value);
        }
    }

}
