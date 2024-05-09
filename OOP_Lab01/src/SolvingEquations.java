import java.util.Scanner;
import static java.lang.Math.sqrt;

public class SolvingEquations {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("1. Linear equation with one variable");
        System.out.println("2. Linear system with two variables");
        System.out.println("3. Second-degree equation with one variable");
        System.out.println("Enter an appropriate number (1, 2, 3) to choose type of equation: ");
        int choice = input.nextInt();

        if (choice == 1) {
            System.out.println("Enter the coefficients for the linear equation (ax + b = 0):");
            System.out.print("Enter coefficient a: ");
            double a = input.nextDouble();
            System.out.print("Enter coefficient b: ");
            double b = input.nextDouble();
            if (a == 0) {
                if (b == 0) {
                    System.out.println("Infinite solutions");
                } else {
                    System.out.println("No solutions");
                }
            } else {
                System.out.println("Solution: x = " + (-b/a));
            }
        } else if (choice == 2) {
            System.out.println("Enter the coefficients for the linear system (a1x + b1y = c1) and (a2x + b2y = c2)");
            System.out.print("Enter coefficient a1: ");
            double a1 = input.nextDouble();
            System.out.print("Enter coefficient b1: ");
            double b1 = input.nextDouble();
            System.out.print("Enter coefficient c1: ");
            double c1 = input.nextDouble();
            System.out.print("Enter coefficient a2: ");
            double a2 = input.nextDouble();
            System.out.print("Enter coefficient b2: ");
            double b2 = input.nextDouble();
            System.out.print("Enter coefficient c2: ");
            double c2 = input.nextDouble();

            double m1 = a1 * b2 - a2 * b1;
            double m2 = c1 * b2 - c2 * b1;
            double m3 = c2 * a1 - c1 * a2;

            if (m1 == 0) {
                if (m2 == 0 | m3 == 0){
                    System.out.println("Infinite solutions");
                } else {
                    System.out.println("No solutions");
                }
            } else {
                System.out.println("Solution: x = " + m2/m1 + "; y = " + m3/m1);
            }
        } else if (choice == 3){
            System.out.println("Enter the coefficients for the Second-degree equation: ax^2 + bx + c =0");
            System.out.print("Enter coefficient a: ");
            double a = input.nextDouble();
            System.out.print("Enter coefficient b: ");
            double b = input.nextDouble();
            System.out.print("Enter coefficient c: ");
            double c = input.nextDouble();

            double delta = b*b - 4 * a * c;

            if (a == 0) {
                if (b == 0) {
                    if (c == 0) {
                        System.out.println("Infinite solutions");
                    } else {
                        System.out.println("No solutions");
                    }
                } else {
                    System.out.println("Solution: " + -c/b);
                }
            } else {
                if (delta < 0){
                    System.out.println("No solutions");
                } else if (delta == 0){
                    System.out.println("Solution: " + (-b/(2*a)));
                } else {
                    System.out.println("Solution: x1 = " + (-b + sqrt(delta)) / (2*a) +
                            "; x2 = " + (-b - sqrt(delta)) / (2*a));
                }
            }
        } else {
            System.out.print("Invalid choice");
        }
        System.exit(0);
    }
}