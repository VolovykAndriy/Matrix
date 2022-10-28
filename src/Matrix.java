import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Matrix {

    private int rows;
    private int cols;
    private float[][] matrix;

    public Matrix() {
        this.matrix = new float[0][0];
    }

    public Matrix(int rows, int cols) {
        if (rows >= 0 && cols >= 0) {
            this.matrix = new float[rows][cols];
            this.rows = rows;
            this.cols = cols;
        } else {
            throw new IllegalArgumentException("Розмірність матриці має бути не від'ємна");
        }
    }

    public Matrix(float[] @NotNull [] matrix) {
        this.rows = matrix.length;
        this.cols = matrix[0].length;
        this.matrix = new float[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, matrix[0].length);
        }
    }

    public float[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(float[][] matrix) {
        this.matrix = matrix;
    }

    public void setElement(int row, int col, float element) {
        this.matrix[row][col] = element;
    }

    public float getElement(int row, int col) {
        return this.matrix[row][col];
    }

    public void randomFillMatrix() {
        Random randomValue = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = randomValue.nextFloat(100);
            }
        }
    }

    public float[] getRow(int row) {
        /*Matrix indexing from [0][0]*/
        float[] rowToReturn = new float[cols];
        System.arraycopy(matrix[row], 0, rowToReturn, 0, cols);
        return rowToReturn;
    }

    public float[] getCol(int col) {
        /*Matrix indexing from [0][0]*/
        float[] colToReturn = new float[rows];
        for (int i = 0; i < rows; i++) {
            colToReturn[i] = this.matrix[i][col];
        }
        return colToReturn;
    }

    public int[] getDimension() {
        return new int[]{rows, cols};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix1 = (Matrix) o;
        return rows == matrix1.rows && cols == matrix1.cols && Arrays.deepEquals(getMatrix(), matrix1.getMatrix());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(rows, cols);
        result = 31 * result + Arrays.deepHashCode(getMatrix());
        return result;
    }

    @Contract("_ -> new")
    public static @NotNull Matrix getDiagonalMatrix(float @NotNull [] vector) {
        float[][] diagonalMatrix = new float[vector.length][vector.length];
        for (int i = 0; i < vector.length; i++) {
            diagonalMatrix[i][i] = vector[i];
        }
        return new Matrix(diagonalMatrix);
    }

    private void transposeMatrix() {
        for (int i = 0; i < rows; i++) {
            for (int j = i + 1; j < cols; j++) {
                float temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    private void swapTwoRows(int row1, int row2) {
        float[] temp = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = temp;
    }

    private boolean checkMatrixSquareness() {
        boolean checkZeroRow = true;
        boolean checkZeroCol = true;
        if (rows != cols) {
            return false;
        }
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] != 0) {
                checkZeroRow = false;
            }
            if (matrix[0][i] != 0) {
                checkZeroCol = false;
            }
        }
        return checkZeroCol == checkZeroRow;
    }

    public void convertToUpTriangle() {
        if (!checkMatrixSquareness()) {
            throw new IllegalArgumentException("Matrix must be square.");
        }
        for (int dim = 0; dim < rows; dim++) {
            for (int i = dim; i < rows; i++) {
                if (matrix[i][dim] != 0 && i != dim && matrix[dim][dim] == 0) {
                    swapTwoRows(dim, i);
                    break;
                }
            }
            float leadElement = matrix[dim][dim];
            for (int i = dim; i < cols; i++){
                if (leadElement != 0) {
                    matrix[dim][i] /= leadElement;
                }
            }
            for (int i = dim+1; i < rows; i++) {
                leadElement = matrix[i][dim];
                for (int j = dim; j < cols; j++) {
                    matrix[i][j] -= matrix[dim][j]*leadElement;
                }
            }
        }
    }

    public void convertToLowerTriangle() {
        transposeMatrix();
        convertToUpTriangle();
        transposeMatrix();
    }
}
