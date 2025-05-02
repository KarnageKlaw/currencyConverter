% rm currencyConverter/*.class
javac -cp .:json-20231013.jar currencyConverter/*.java
java -cp .:json-20231013.jar currencyConverter.CurrencyConverterApp
