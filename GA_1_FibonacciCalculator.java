import java.util.Scanner;

public class FibonacciCalculator {

    // Recursive approach to calculate Fibonacci number
    public static int fibonacciRecursive(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
        }
    }

    // Iterative approach to calculate Fibonacci number
    public static int fibonacciIterative(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }

        int a = 0, b = 1;
        int result = 0;

        for (int i = 2; i <= n; i++) {
            result = a + b;
            a = b;
            b = result;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Fibonacci sequence number (n): ");
        int n = scanner.nextInt();

        System.out.println("Choose method:");
        System.out.println("1. Recursive");
        System.out.println("2. Iterative");
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.print("Fibonacci Recursive: ");
            for (int i = 0; i < n; i++) {
                System.out.print(fibonacciRecursive(i) + " ");
            }
        } else if (choice == 2) {
            System.out.print("Fibonacci Iterative: ");
            for (int i = 0; i < n; i++) {
                System.out.print(fibonacciIterative(i) + " ");
            }
        } else {
            System.out.println("Invalid choice");
        }

        scanner.close();
    }
}
