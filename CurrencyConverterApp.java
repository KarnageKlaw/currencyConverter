package currencyConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CurrencyConverterApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CurrencyConverterApp().createUI());
    }

    private void createUI() {
        JFrame frame = new JFrame("Currency Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(new GridBagLayout());

        String[] currencyCodes = {"USD", "EUR", "GBP", "CHF", "CNY", "JPY", "INR", "AUD", "CAD"};
        JComboBox<String> fromBox = new JComboBox<>(currencyCodes);
        JComboBox<String> toBox = new JComboBox<>(currencyCodes);
        JTextField amountField = new JTextField(10);
        JButton convertButton = new JButton("Convert");
        JLabel resultLabel = new JLabel("Result will appear here");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        frame.add(new JLabel("From:"), gbc);
        gbc.gridx = 1;
        frame.add(fromBox, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        frame.add(new JLabel("To:"), gbc);
        gbc.gridx = 1;
        frame.add(toBox, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        frame.add(new JLabel("Amount:"), gbc);
        gbc.gridx = 1;
        frame.add(amountField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        frame.add(convertButton, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        frame.add(resultLabel, gbc);

        convertButton.addActionListener(e -> {
            try {
                String from = (String) fromBox.getSelectedItem();
                String to = (String) toBox.getSelectedItem();
                double amount = Double.parseDouble(amountField.getText());

                double rate = CurrencyAPI.getLiveRate(from, to);
                if (rate == 0.0) {
                    resultLabel.setText("Failed to fetch rate.");
                    return;
                }

                double converted = Math.round(amount * rate * 100.0) / 100.0;
                resultLabel.setText(amount + " " + from + " = " + converted + " " + to);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter a valid number.");
            }
        });

        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }
}
