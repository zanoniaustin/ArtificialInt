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
public class SteepestDescentMinimizer implements Minimizer {

    double eps = 1e-6;

    RealMin problem;

    @Override
    public void setProblem(RealMin _problem) {
        problem = _problem;
    }

    class SteepestDescentRealMin implements RealMin { // g(t)

        int n = problem.getRealParameterSize();
        double[] x = new double[n];
        double[] grad = new double[n];
        double t;

        SteepestDescentRealMin() {
            setup();
        }

        SteepestDescentRealMin(SteepestDescentRealMin copy) {
            System.arraycopy(copy.x, 0, x, 0, n);
            System.arraycopy(copy.grad, 0, grad, 0, n);
            t = copy.t;
        }

        void setup() {
            for (int i = 0; i < n; ++i) {
                double xi = problem.getRealParameterValue(i);
                problem.setRealParameterValue(i, xi + eps);
                double yp = problem.getValue();
                problem.setRealParameterValue(i, xi - eps);
                double ym = problem.getValue();
                grad[i] = (yp - ym) / (2 * eps);
                problem.setRealParameterValue(i, xi);
                x[i] = xi;
            }
            t = 0;
        }

        @Override
        public SteepestDescentRealMin copy() {
            return new SteepestDescentRealMin(this);
        }

        @Override
        public double getValue() {
            int dim = problem.getRealParameterSize();
            for (int i = 0; i < dim; ++i) {
                problem.setRealParameterValue(i, x[i] + t * grad[i]);
            }
            return problem.getValue();
        }

        @Override
        public int getRealParameterSize() {
            return 1;
        }

        @Override
        public String getRealParameterName(int index) {
            if (index == 0) {
                return "t";
            }
            throw new IndexOutOfBoundsException();
        }

        @Override
        public int getRealParameterIndex(String name) {
            if (name.equals("t")) {
                return 0;
            }
            throw new IndexOutOfBoundsException();
        }

        @Override
        public double getRealParameterValue(int index) {
            if (index == 0) {
                return t;
            }
            throw new IndexOutOfBoundsException();
        }

        @Override
        public void setRealParameterValue(int index, double value) {
            if (index == 0) {
                t = value;
                return;
            }
            throw new IndexOutOfBoundsException();
        }

        void config(GoldenSectionMinimizer minimizer) {
            problem.setRealParameterValue(0, 0);
            double y0 = problem.getValue();
            problem.setRealParameterValue(0, eps);
            double yp = problem.getValue();
            problem.setRealParameterValue(0, -eps);
            double ym = problem.getValue();
            double dy = (yp - ym) / (2 * eps);
            double dy2 = (-2 * y0 + yp + ym) / (eps * eps);
            double dx = Math.abs(dy / dy2);
            minimizer.a = -1.25*dx;
            minimizer.b =  0.25*dx;
        }

    }

    @Override
    public double min() {
        double minimum;
        double[] x0 = new double[problem.getRealParameterSize()];
        double[] x1 = new double[problem.getRealParameterSize()];
        for (int i = 0; i < x0.length; ++i) {
            x0[i] = problem.getRealParameterValue(i);
        }
        SteepestDescentRealMin g = new SteepestDescentRealMin();
        GoldenSectionMinimizer min1d = new GoldenSectionMinimizer();
        for (;;) {
            g.setup(); // calc grad f at x now to define g(t) = f(x+t*grad f)
            min1d.setProblem(g);
            min1d.eps = eps;
            g.config(min1d); // use g',g'' to estimate [a,b] for min problem

            minimum=min1d.min(); // find min for g (t star)
            for (int i = 0; i < x0.length; ++i) {
                x1[i] = problem.getRealParameterValue(i);
            }

            double err = 0;
            for (int i = 0; i < x0.length; ++i) {
                err = Math.max(err, Math.abs(x0[i] - x1[i]));
            }
            if (err <= eps) {
                break;
            }
            double[] tmp = x1;
            x1 = x0;
            x0 = tmp;
        }
        return minimum;
    }
}
