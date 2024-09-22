 public class Polynomial { 

    double[] polynomial;

    public Polynomial () {
        this.polynomial = new double[]{0};
    }

    public Polynomial (double[] polynomial) {
        this.polynomial = polynomial;
    }

    public Polynomial add(Polynomial to_add) {

        int max_length = Math.max(polynomial.length, to_add.polynomial.length);
        double[] polynomial_sum = new double[max_length];

        for (int i = 0; i < max_length; i++) {
            polynomial_sum[i] = (i < polynomial.length ? polynomial[i] : 0) + (i < to_add.polynomial.length ? to_add.polynomial[i] : 0);
        }

        return new Polynomial(polynomial_sum);
    }

    public double evaluate(double x) {
        double total = 0;

        for (int i = 0; i < polynomial.length; i++) {
            total += Math.pow(x, i+1) * polynomial[i];
        }

        return total;
    }

    public boolean hasRoot(double x) {
        return this.evaluate(x) == 0;
    }

}