import java.util.Scanner;

public class Main {

//    public static void showMatrix(float[][] matrix) {
//        for (float[] floats : matrix) {
//            for (int j = 0; j < matrix[0].length; j++) {
//                System.out.print(floats[j] + " | ");
//            }
//            System.out.print("\n");
//        }
//        System.out.print("\n");
//    }
//
//    public static void setGenericMatrix(GenericMatrix<String> genericMatrix){
//        Scanner scanner = new Scanner(System.in);
//        for (int i = 0; i < genericMatrix.getDimension()[0]; i++) {
//            for (int j = 0; j < genericMatrix.getDimension()[1]; j++) {
//                System.out.print("Enter matrix[" + i + "]["+ j +"]: ");
//                genericMatrix.setElement(i, j, scanner.nextLine());
//            }
//        }
//    }
//
//    public static void setMatrix(Matrix matrix) {
//        Scanner scanner = new Scanner(System.in);
//        for (int i = 0; i < matrix.getDimension()[0]; i++) {
//            for (int j = 0; j < matrix.getDimension()[1]; j++) {
//                System.out.print("Enter matrix[" + i + "]["+ j +"]: ");
//                matrix.setElement(i, j, scanner.nextFloat());
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        Matrix matrix = new Matrix(3, 3);
//        matrix.randomFillMatrix();
//        Matrix matrix1 = new Matrix(matrix.getMatrix());
//        System.out.println(matrix.toString());
//        GenericMatrix<String> genericMatrix = new GenericMatrix<>(3, 3, String.class);
//        genericMatrix.setElement(1,1,"aboba");
//        genericMatrix.showGenericMatrix();
//    }
}