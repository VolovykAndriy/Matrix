import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public final class MatrixImmutable {
    private int rows;
    private int cols;
    private final float[][] matrix;

    public MatrixImmutable() {
        this.matrix = new float[0][0];
    }

    public MatrixImmutable(int rows, int cols) {
        if (rows >= 0 && cols >= 0) {
            this.matrix = new float[rows][cols];
            this.rows = rows;
            this.cols = cols;
        } else {
            throw new IllegalArgumentException("Розмірність матриці має бути не від'ємна");
        }
    }

    public MatrixImmutable(float[] @NotNull [] matrix) {
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

    public float getElement(int row, int col) {
        return this.matrix[row][col];
    }

    public float @NotNull [] getRow(int row) {
        /*Matrix indexing from [0][0]*/
        float[] rowToReturn = new float[cols];
        System.arraycopy(matrix[row], 0, rowToReturn, 0, cols);
        return rowToReturn;
    }

    @Contract(pure = true)
    public float @NotNull [] getCol(int col) {
        /*Matrix indexing from [0][0]*/
        float[] colToReturn = new float[rows];
        for (int i = 0; i < rows; i++) {
            colToReturn[i] = this.matrix[i][col];
        }
        return colToReturn;
    }

    @Contract(value = " -> new", pure = true)
    public int @NotNull [] getDimension() {
        return new int[]{rows, cols};
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MatrixImmutable matrix = (MatrixImmutable) obj;
        if (this.matrix.length != ((MatrixImmutable) obj).getMatrix().length) {
            return false;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (this.matrix[i][j] != matrix.getMatrix()[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(matrix);
    }
}
