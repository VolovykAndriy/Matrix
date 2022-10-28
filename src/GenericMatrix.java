import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

public class GenericMatrix<T> {
    private int rows;
    private int cols;
    private T[][] genericMatrix;

//    Class<T> typeClass;

    public GenericMatrix(int rows, int cols, Class<T> typeClass) {
        this.genericMatrix = (T[][]) Array.newInstance(typeClass, rows, cols);
    }

    public GenericMatrix(T[][] genericMatrix) {
        this.genericMatrix = genericMatrix;
    }

    public T[][] getGenericMatrix() {
        return genericMatrix;
    }

    public void setGenericMatrix(T[][] genericMatrix) {
        this.genericMatrix = genericMatrix;
    }

    public void setElement(int row, int col, T element) {
        this.genericMatrix[row][col] = element;
    }

    public T getElement(int row, int col) {
        return this.genericMatrix[row][col];
    }

    public T[] getRow(int row, Class<T> typeClass) {
        /*Matrix indexing from [0][0]*/
        T[] rowToReturn = (T[]) Array.newInstance(typeClass, cols);
        System.arraycopy(genericMatrix[row], 0, rowToReturn, 0, cols);
        return rowToReturn;
    }

    public T[] getCol(int col, Class<T> typeClass) {
        /*Matrix indexing from [0][0]*/
        T[] colToReturn = (T[]) Array.newInstance(typeClass, rows);
        for (int i = 0; i < rows; i++) {
            colToReturn[i] = this.genericMatrix[i][col];
        }
        return colToReturn;
    }

    public int[] getDimension() {
        return new int[]{rows, cols};
    }

    public void showGenericMatrix(){
        for (T[] matrix : this.genericMatrix) {
            for (T t : matrix) {
                System.out.print(t + " | ");
            }
            System.out.print("\n");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericMatrix<?> that = (GenericMatrix<?>) o;
        return rows == that.rows && cols == that.cols && Arrays.deepEquals(genericMatrix, that.genericMatrix);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(rows, cols);
        result = 31 * result + Arrays.deepHashCode(genericMatrix);
        return result;
    }
}
