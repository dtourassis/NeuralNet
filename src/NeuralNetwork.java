public class NeuralNetwork {

    private int input_nodes;
    private int hidden_nodes;
    private int output_nodes;

    private Matrix weights_ih;
    private Matrix weights_ho;
    private Matrix bias_h;
    private Matrix bias_o;
    private ActivationFunction sigmoid;
    private ActivationFunction derSigmoid;

    private Double learningRate = 0.1;

    public NeuralNetwork(int input_nodes, int hidden_nodes, int output_nodes) {
        this.input_nodes = input_nodes;
        this.hidden_nodes = hidden_nodes;
        this.output_nodes = output_nodes;

        this.weights_ih = new Matrix(this.hidden_nodes, this.input_nodes);
        this.weights_ho = new Matrix(this.output_nodes, this.hidden_nodes);

        this.weights_ih.randomize();
        this.weights_ho.randomize();

        this.bias_h = new Matrix(this.hidden_nodes, 1);
        this.bias_o = new Matrix(this.output_nodes, 1);

        this.bias_h.randomize();
        this.bias_o.randomize();

        sigmoid = new Sigmoid();
        derSigmoid = new derSigmoid();
    }

    public double[] feedForward(double[] inputs_array) {

        // Generate hidden outputs
        Matrix inputs = Matrix.fromArray(inputs_array);
        Matrix hidden = Matrix.product(this.weights_ih, inputs);
        hidden.add(this.bias_h);

        // Activation function
        Sigmoid sigmoid = new Sigmoid();
        hidden.apply(sigmoid);

        // Generate the output's output
        Matrix outputs = Matrix.product(this.weights_ho, hidden);
        outputs.add(this.bias_o);
        outputs.apply(sigmoid);

        return outputs.toArray();
    }

    public void train(double[] inputs_array, double[] targets_array) {

        // Generate hidden outputs
        Matrix inputs = Matrix.fromArray(inputs_array);
        Matrix hidden = Matrix.product(this.weights_ih, inputs);
        hidden.add(this.bias_h);

        // Activation function

        hidden.apply(sigmoid);

        // Generate the output's output
        Matrix outputs = Matrix.product(this.weights_ho, hidden);
        outputs.add(this.bias_o);
        outputs.apply(sigmoid);


        Matrix targets = Matrix.fromArray(targets_array);


        Matrix output_errors = Matrix.subtract(targets, outputs);

        // Calculate gradient
        Matrix gradients = Matrix.apply(outputs, derSigmoid);
        gradients.multiply(output_errors);
        gradients.multiply(learningRate);

        // Calculate deltas
        Matrix hidden_T = Matrix.transpose(hidden);
        Matrix weight_ho_deltas = Matrix.product(gradients,hidden_T);

        // Adjust the weights
        weights_ho.add(weight_ho_deltas);
        // Adjust the bias
        bias_o.add(gradients);


        // Calculate the hidden layer errors
        Matrix who_t = Matrix.transpose(this.weights_ho);
        Matrix hidden_errors = Matrix.product(who_t, output_errors);

        // Calculate hidden gradient
        Matrix hidden_gradient = Matrix.apply(hidden, derSigmoid);
        hidden_gradient.multiply(hidden_errors);
        hidden_gradient.multiply(learningRate);

        // Calculate input -> hidden deltas
        Matrix inputs_T = Matrix.transpose(inputs);
        Matrix weight_ih_deltas = Matrix.product(hidden_gradient, inputs_T);

        // Adjust the weights
        weights_ih.add(weight_ih_deltas);

        // Adjust the bias
        bias_h.add(hidden_gradient);

        //outputs.print();
        //targets.print();
        //output_errors.print();
    }

}
