import java.util.Scanner;

public class B33PBOP {
    private static final String horizontalLine = "_".repeat(50);

    public static void main(String[] args) {
        B33PBOP.greet();
        B33PBOP.echo();
    }

    public static void greet() {
        String greetings =
                horizontalLine + "\n"
                + "I'm B33PBOP...\n"
                + "What do you want?\n"
                + horizontalLine + "\n";
        System.out.println(greetings);
    }

    public static void echo() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            String message =
                    horizontalLine + "\n"
                    + input + "\n"
                    + horizontalLine + "\n";
            System.out.println(message);

            if (input.equalsIgnoreCase("bye")) {
                String goodbyeMessage =
                        horizontalLine + "\n"
                        + "Please leave me alone\n"
                        + horizontalLine + "\n";
                System.out.println(goodbyeMessage);
                break;
            }
        }
    }
}
