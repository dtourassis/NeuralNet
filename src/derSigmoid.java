// Derivative of Sigmoid function
public class derSigmoid implements ActivationFunction {
    @Override
    public double apply(double x) {
        //return ( 1 / (1 + Math.exp((-x))) ) * (1 - (1 / (1 + Math.exp((-x)))));
        return x * (1-x);
    }
}


