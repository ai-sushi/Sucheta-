import java.net.http.*;
import java.net.URI;
import java.io.IOException;
import java.util.Scanner;
import org.json.JSONObject;

public class CurrencyCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Real-Time Currency Calculator!");
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

        String targetCurrency = "";
        switch (option) {
            case 1:
                targetCurrency = "EUR";
                break;
            case 2:
                targetCurrency = "GBP";
                break;
            case 3:
                targetCurrency = "INR";
                break;
            case 4:
                targetCurrency = "JPY";
                break;
        }

        try {
            double exchangeRate = fetchExchangeRate("USD", targetCurrency);
            double convertedAmount = amountInUsd * exchangeRate;
            System.out.printf("%.2f USD is equivalent to %.2f %s.%n", amountInUsd, convertedAmount, targetCurrency);
        } catch (Exception e) {
            System.out.println("Error fetching real-time exchange rates: " + e.getMessage());
        }

        System.out.println("Thank you for using the Real-Time Currency Calculator!");
        scanner.close();
    }

    private static double fetchExchangeRate(String baseCurrency, String targetCurrency) throws IOException, InterruptedException {
        String apiUrl = String.format("https://api.exchangerate-api.com/v4/latest/%s", baseCurrency);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Failed to fetch exchange rates. HTTP status code: " + response.statusCode());
        }

        JSONObject json = new JSONObject(response.body());
        return json.getJSONObject("rates").getDouble(targetCurrency);
    }
}
