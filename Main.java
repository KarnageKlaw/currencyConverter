package currencyConverter;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Currency> currencies = Currency.init();
        Scanner scanner = new Scanner(System.in);

        // Display available currencies
        System.out.println("Available currencies:");
        for (int i = 0; i < currencies.size(); i++) {
            System.out.println(i + ": " + currencies.get(i).getName() + " (" + currencies.get(i).getShortName() + ")");
        }

        // Input: source currency
        System.out.print("Enter the number for source currency: ");
        int sourceIndex = scanner.nextInt();

        // Input: target currency
        System.out.print("Enter the number for target currency: ");
        int targetIndex = scanner.nextInt();

        // Input: amount
        System.out.print("Enter amount in " + currencies.get(sourceIndex).getShortName() + ": ");
        double amount = scanner.nextDouble();

        // Conversion
        String targetShortName = currencies.get(targetIndex).getShortName();
        double exchangeRate = currencies.get(sourceIndex).getExchangeRate(targetShortName);
        double converted = Currency.convert(amount, exchangeRate);

        // Output
        System.out.println(amount + " " + currencies.get(sourceIndex).getShortName() +
                " is equal to " + converted + " " + targetShortName);

        scanner.close();
    }
}
