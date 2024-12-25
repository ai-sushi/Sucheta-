import java.util.Scanner;

public class CurrencyCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Exchange rates (example rates, update as needed)
        double usdToEur = 0.93;
        double usdToGbp = 0.76;
        double usdToInr = 82.65;
        double usdToJpy = 144.50;

        System.out.println("Welcome to the Currency Calculator!");
        System.out.println("Available currencies:");
        System.out.println("1. USD to EUR");
        System.out.println("2. USD to GBP");
        System.out.println("3. USD to INR");
        System.out.println("4. USD to JPY");
        System.out.println("Select a conversion option (1-4):");

        int option = scanner.nextInt();

        if (option < 1 || option > 4) {
            System.out.println("Invalid option. Please restart the program and try again.");
            scanner.close();
            return;
        }

        System.out.println("Enter the amount in USD:");
        double amountInUsd = scanner.nextDouble();

        double convertedAmount = 0.0;
        String targetCurrency = "";

        switch (option) {
            case 1:
                convertedAmount = amountInUsd * usdToEur;
                targetCurrency = "EUR";
                break;
            case 2:
                convertedAmount = amountInUsd * usdToGbp;
                targetCurrency = "GBP";
                break;
            case 3:
                convertedAmount = amountInUsd * usdToInr;
                targetCurrency = "INR";
                break;
            case 4:
                convertedAmount = amountInUsd * usdToJpy;
                targetCurrency = "JPY";
                break;
        }

        System.out.printf("%.2f USD is equivalent to %.2f %s.%n", amountInUsd, convertedAmount, targetCurrency);

        System.out.println("Thank you for using the Currency Calculator!");
        scanner.close();
    }
}
