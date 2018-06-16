// Sigmoid function
public class Sigmoid implements ActivationFunction {
    @Override
    public double apply(double x) {
        return 1 / (1 + Math.exp((-x)));
    }
}
