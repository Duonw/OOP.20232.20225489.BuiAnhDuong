import java.util.Scanner;
public class AddTwoMatrices {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of rows: ");
        int rows = input.nextInt();
        System.out.print("Enter the number of columns: ");
        int cols = input.nextInt();

        double[][] ma1 = new double[rows][cols];
        double[][] ma2 = new double[rows][cols];
        double[][] ma3 = new double[rows][cols];

        System.out.println("Enter the elements of the first matrix:");
        for (int i = 0; i < rows; i ++){
            for (int j = 0; j < cols; j ++){
                System.out.print("Element at (" + (i+1) + ", " + (j+1) + "): ");
                ma1[i][j] = input.nextDouble();
            }
        }

        System.out.println("Enter the elements of the second matrix:");
        for (int i = 0; i < rows; i ++){
            for (int j = 0; j < cols; j ++){
                System.out.print("Element at (" + (i+1) + ", " + (j+1) + "): ");
                ma2[i][j] = input.nextDouble();
            }
        }

        System.out.println("The result is:");
        for (int i = 0; i < rows; i ++){
            for (int j = 0; j < cols; j ++){
                ma3[i][j] = ma1[i][j] + ma2[i][j];
            }
        }

        for (int i = 0; i < rows; i ++){
            String rowi = "";
            for (int j = 0; j < cols; j ++){
                rowi = rowi + Double.toString(ma3[i][j]) + " ";
            }
            System.out.println(rowi);
        }
    }
}
