package PackageCollatz;

import java.io.PrintStream;
import java.util.Scanner;

public class ClassCollatz {
    PrintStream out;
    Scanner in;

    ClassCollatz () {
        out = new PrintStream(System.out);
        in = new Scanner(System.in);
    }

    void start() {
        out.printf("Enter a positive integer: ");
        int variable1 = in.nextInt();

        out.printf("%d ", variable1);
        while (variable1 != 1) {
            int variable2 = variable1 % 2;

            if (variable2 == 0) {
                variable1 = (variable1 / 2);
            } else {
                variable1 = (3 * variable1 + 1);
            }
            out.printf("%d ", variable1);
        }

    }

    public static void main(String[] argv) {
        new ClassCollatz().start();
    }

}