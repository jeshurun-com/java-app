public class Main {

    public static void main(String[] args) {
        greetUser();

        variableDeclarationAndInitialization();

        // Updating Variables
        updatingVariables();

        // Compound assignment operators
        compoundAssignmentOperators();

        // Evaluate Expressions
        evaluateExpressions();
    }

    public static void greetUser() {
        System.out.println("Hello");
        System.out.println("Yeshurun");
    }

    public static void variableDeclarationAndInitialization() {
        int count = 10;
        System.out.println("Count: " + count);
    }

    public static void updatingVariables() {
        double price = 25.0;
        price *= 1.1; // Increase by 10%
        System.out.println("Updated Price: " + price);
    }

    public static void compoundAssignmentOperators() {
        int a = 10;
        int b = 5;

        a += b;
        System.out.println("Compound Addition (a += b): " + a);

        a -= b;
        System.out.println("Compound Subtraction (a -= b): " + a);

        a *= b;
        System.out.println("Compound Multiplication (a *= b): " + a);

        a /= b;
        System.out.println("Compound Division (a /= b): " + a);

        a %= b;
        System.out.println("Compound Modulo (a %= b): " + a);
    }

    public static void evaluateExpressions() {
        int result1 = 7 + (24 / 8) * 4 + 6;
        System.out.println("Expression 1: 7 + (24 / 8) * 4 + 6 = " + result1);

        int result2 = (5 + 2 * 3) / 2;
        System.out.println("Expression 2: (5 + 2 * 3) / 2 = " + result2);

        int result3 = 15 - (6 + 3) * 4;
        System.out.println("Expression 3: 15 - (6 + 3) * 4 = " + result3);

        int result4 = 12 - 3 * (4 - 1);
        System.out.println("Expression 4: 12 - 3 * (4 - 1) = " + result4);

        int result5 = (10 - 3) * (2 * 2 + 1);
        System.out.println("Expression 5: (10 - 3) * (2 * 2 + 1) = " + result5);
    }
}

