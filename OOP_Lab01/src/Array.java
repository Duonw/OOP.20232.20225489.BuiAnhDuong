import java.util.Arrays;
import java.util.Scanner;
public class Array {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the length of the array: ");
        int n = input.nextInt();
        System.out.println("Enter the elements of the array:");
        double[] myArray = new double[n];
        for (int i = 0; i < n; i ++){
            myArray[i] = input.nextDouble();
        }
        Arrays.sort(myArray);
        System.out.println("The sorted array is:\n" + Arrays.toString(myArray));

        double sum = 0;
        for (int i = 0; i < n; i ++){
            sum += myArray[i];
        }
        System.out.println("Sum of array elements: " + sum);
        System.out.println("Average of array elements: " + sum/n);
    }
}