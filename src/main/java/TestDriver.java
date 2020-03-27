import java.util.Scanner;

public class TestDriver {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean quit = false;
        BigInteger result;
        BigInteger entered;
        System.out.print("Enter an initial number: ");
        result = new BigInteger(in.nextLine());
        while(!quit) {
            System.out.print("Enter an operation (+, -, /, *, ^, q to quit)");
            String operation = in.nextLine();
            if(operation.equals("q")) {
                quit = true;
            } else {
                System.out.print("Enter another number to perform this operation: ");
                entered = new BigInteger(in.nextLine());
                result = performOperation(result, entered, operation);
                System.out.println("New result: " + result.toString());
            }
        }
    }

    private static BigInteger performOperation(BigInteger firstInt, BigInteger secondInt, String operation) {
        if(operation.equals("+")) {
            return firstInt.add(secondInt);
        } else if(operation.equals("-")) {
            return firstInt.subtract(secondInt);
        } else if(operation.equals("*")) {
            return firstInt.multiply(secondInt);
        } else if(operation.equals("/")) {
            return firstInt.divide(secondInt);
        } else if(operation.equals("^")) {
            return firstInt.power(secondInt);
        } else {
            throw new IllegalArgumentException("Not an operation: " + operation);
        }
    }
}
