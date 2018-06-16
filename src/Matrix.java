public class Matrix {

    private int id = -1;
    private int rows;
    private int cols;
    private double[][] data;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows][cols];
        initializeZero();
    }

    public Matrix(int id, int rows, int cols) {
        this.id = id;
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows][cols];
        initializeZero();
    }

    // Matrix operations
    public void multiply(Object n) {
        // Scalar
        if (n instanceof Number) {
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    data[i][j] *= (Double) n;
                }
            }
        }
        // Element wise (Hadamard / Schur product)
        else if (n instanceof Matrix) {
            if (rows == ((Matrix) n).rows && cols == ((Matrix) n).cols) {
                for (int i = 0; i < data.length; i++) {
                    for (int j = 0; j < data[i].length; j++) {
                        data[i][j] *= ((Matrix) n).data[i][j];
                    }
                }
            } else {
                throw new IllegalArgumentException("Matrices are not of the same size!");
            }
        } else {
            throw new IllegalArgumentException("Object n neither a number of a data!");
        }
    }

    public void add(Object n) {
        // Scalar
        if (n instanceof Number) {
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    data[i][j] += (Double) n;
                }
            }
        }
        // Element wise
        else if (n instanceof Matrix) {
            if (rows == ((Matrix) n).rows && cols == ((Matrix) n).cols) {
                for (int i = 0; i < data.length; i++) {
                    for (int j = 0; j < data[i].length; j++) {
                        data[i][j] += ((Matrix) n).data[i][j];
                    }
                }
            } else {
                throw new IllegalArgumentException("Matrices are not of the same size!");
            }
        } else {
            throw new IllegalArgumentException("Object n neither a number of a data!");
        }
    }

    public static Matrix subtract(Matrix m1, Matrix m2) {
        Matrix result = new Matrix(m1.rows, m1.cols);
        for (int i = 0; i < result.rows; i++) {
            for (int j = 0; j < result.cols; j++) {
                result.setValue(i,j,m1.getValue(i,j) - m2.getValue(i,j));
            }
        }
        return result;
    }


    public static Matrix product(Matrix m1, Matrix m2) {
        // Matrix product
        if(m1.cols == m2.rows ) {
            Matrix result = new Matrix(m1.rows, m2.cols);
            for (int i = 0; i < result.rows; i++) {
                for (int j = 0; j < result.cols; j++) {
                    double sum = 0;
                    for (int k = 0; k < m1.cols; k++) {
                        sum += m1.data[i][k] * m2.data[k][j];
                    }
                    result.data[i][j] = sum;
                }
            }
            return result;
        } else {
            throw new IllegalArgumentException("Columns of matrix " + m1.id + " must match rows of matrix " + m2.id + "!");
        }
    }

    public static Matrix transpose(Matrix m) {
        Matrix result = new Matrix(m.cols, m.rows);
        for (int i = 0; i < m.rows; i++) {
            for (int j = 0; j < m.cols; j++) {
                result.data[j][i] = m.data[i][j];
            }
        }
        return result;
    }

    // Handy stuff

    public void apply(ActivationFunction af) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = af.apply(data[i][j]);
            }
        }
    }

    public static Matrix apply(Matrix m, ActivationFunction af) {
        Matrix result = new Matrix(m.rows, m.cols);
        for (int i = 0; i < m.rows; i++) {
            for (int j = 0; j < m.cols; j++) {
                result.data[i][j] = af.apply(m.getValue(i,j));
            }
        }
        return result;
    }

    // Sets all values of a matrix to zero.
    private void initializeZero() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = 0;
            }
        }
    }

    // Set each value of a matrix to a random
    // one between -1 and 1.
    public void randomize() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = Math.random() * 2 - 1;
            }
        }
    }

    public static Matrix fromArray(double[] array) {
        Matrix m = new Matrix(array.length, 1);
        for (int i = 0; i < array.length; i++) {
          m.data[i][0] = array[i];
        }
        return m;
    }

    public double[] toArray() {
        double[] array = new double[data.length * data[0].length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                array[i] = data[i][j];
            }
        }
        return array;
    }

    // Prints the contents of a matrix.
    public void print(){
        System.out.println("Matrix " + id + " :");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Returns the value of the cell specified
    // by i and j.
    public double getValue(int i, int j) {
        return this.data[i][j];
    }

    // Sets the value of the cell specified
    // by i and j to newValue
    public void setValue(int i, int j, double newValue) {
        this.data[i][j] = newValue;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

}
