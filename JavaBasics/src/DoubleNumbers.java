import java.util.Scanner;
public class DoubleNumbers {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the 1st number: ");
        double num1 = input.nextDouble();
        System.out.print("Enter the 2nd number: ");
        double num2 = input.nextDouble();
        System.out.println("Sum: " + (num1 + num2));
        System.out.println("Difference: " + (num1 - num2));
        System.out.println("Product: " + (num1 * num2));
        if (num2 == 0){
            System.out.println("Cannot calculate quotient as the divisor is 0");
        }
        else{
            System.out.println("Quotient: " + (num1 / num2));
        }
        System.exit(0);
    }

}