package labmid;

import java.util.Scanner;
import java.util.Stack;

public class ExpressionConverter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String expr = sc.nextLine().trim();
        String postfix = infixToPostfix(expr);
        String prefix = infixToPrefix(expr);
        System.out.println("Postfix: " + postfix);
        System.out.println("Prefix: " + prefix);
        sc.close();
    }

    static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    static int prec(char c) {
        if (c == '+' || c == '-') return 1;
        if (c == '*' || c == '/') return 2;
        if (c == '^') return 3;
        return -1;
    }

    static String infixToPostfix(String infix) {
        StringBuilder output = new StringBuilder();
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);
            if (c == ' ' || c == '\t') continue;
            if (Character.isLetterOrDigit(c)) {
                output.append(c);
                output.append(' ');
            } else if (c == '(') {
                st.push(c);
            } else if (c == ')') {
                while (!st.isEmpty() && st.peek() != '(') {
                    output.append(st.pop()).append(' ');
                }
                if (!st.isEmpty() && st.peek() == '(') st.pop();
            } else if (isOperator(c)) {
                while (!st.isEmpty() && isOperator(st.peek()) &&
                        ((prec(c) <= prec(st.peek()) && c != '^') || (prec(c) < prec(st.peek()) && c == '^'))) {
                    output.append(st.pop()).append(' ');
                }
                st.push(c);
            }
        }
        while (!st.isEmpty()) output.append(st.pop()).append(' ');
        return output.toString().trim();
    }

    static String infixToPrefix(String infix) {
        StringBuilder reversed = new StringBuilder();
        for (int i = infix.length() - 1; i >= 0; i--) {
            char c = infix.charAt(i);
            if (c == '(') reversed.append(')');
            else if (c == ')') reversed.append('(');
            else reversed.append(c);
        }
        String rev = reversed.toString();
        String postfix = infixToPostfix(rev);
        StringBuilder prefix = new StringBuilder();
        String[] tokens = postfix.split("\\s+");
        for (int i = tokens.length - 1; i >= 0; i--) {
            prefix.append(tokens[i]);
            if (i > 0) prefix.append(' ');
        }
        return prefix.toString();
    }
}

