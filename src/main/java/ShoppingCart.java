import java.util.*;

public class ShoppingCart {
    public static void main(String[] args) {
        System.out.println("Select language:");
        System.out.println("1. English\n" +
                "2. Finnish\n" +
                "3. Swedish\n" +
                "4. Japanese");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        Locale locale;
        switch (choice) {
            case 1:
                locale = new Locale("en", "US");
                break;
            case 2:
                locale = new Locale("fi", "FI");
                break;
            case 3:
                locale = new Locale("sv", "SE");
                break;
            case 4:
                locale = new Locale("ja", "JP");
                break;
            default:
                System.out.println("Invalid choice. Using English as default.");
                locale = new Locale("en", "US");
                break;
        }

        ResourceBundle messages;
        try {
            messages = ResourceBundle.getBundle("messages", locale);
        } catch (Exception e) {
            System.out.println("Invalid language. Using English as default.");
            messages = ResourceBundle.getBundle("messages", new Locale("en", "US"));
        }

        System.out.println(messages.getString("items"));
        int numItems = scanner.nextInt();

        double totalCost = 0.0;

        for (int i = 1; i <= numItems; i++) {
            System.out.println(messages.getString("price") + " " + i + ": ");
            double price = scanner.nextDouble();

            System.out.println(messages.getString("quantity") + " " + i + ": ");
            int quantity = scanner.nextInt();

            totalCost += price * quantity;
        }

        System.out.println(messages.getString("cost") + " " + totalCost);

        scanner.close();
    }
}