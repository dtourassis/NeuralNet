import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {

        // Create NN
        NeuralNetwork nn = new NeuralNetwork(2,2,1);

        // Data set (XOR)
        double[] inputs0 = {0,0};
        double[] targets0 = {0};

        double[] inputs1 = {0,1};
        double[] targets1 = {1};

        double[] inputs2 = {1,0};
        double[] targets2 = {1};

        double[] inputs3 = {1,1};
        double[] targets3 = {0};

        ArrayList<double[]> inputs = new ArrayList();
        inputs.add(inputs0);
        inputs.add(inputs1);
        inputs.add(inputs2);
        inputs.add(inputs3);

        ArrayList<double[]> targets = new ArrayList();
        targets.add(targets0);
        targets.add(targets1);
        targets.add(targets2);
        targets.add(targets3);

        // Train NN on the specified data set
        for (int i = 0; i < 1000000; i++) {
            int sel = (int) (Math.random()*4);
            nn.train(inputs.get(sel), targets.get(sel));
        }

        // Get output from the NN
        double[] output0 = nn.feedForward(inputs0);
        for (int i = 0; i < output0.length; i++) {
            System.out.println(output0[i]);
        }

        double[] output1 = nn.feedForward(inputs1);
        for (int i = 0; i < output1.length; i++) {
            System.out.println(output1[i]);
        }

        double[] output2 = nn.feedForward(inputs2);
        for (int i = 0; i < output2.length; i++) {
            System.out.println(output2[i]);
        }

        double[] output3 = nn.feedForward(inputs3);
        for (int i = 0; i < output3.length; i++) {
            System.out.println(output3[i]);
        }

    }
}
