package CurrencyConversion.CurrencyConversion;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Scanner;

public class RealTimeCurrencyConverter {

    private static final OkHttpClient client = new OkHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    
    private static final String API_KEY = "993fd4676ed8626a58320bde04aa72d6";

    private static double convert(String from, String to, double amount) throws IOException {
    	String url = String.format(
    		    "https://api.exchangerate.host/convert?access_key=%s&from=%s&to=%s&amount=%s",
    		    API_KEY, from, to, String.valueOf(amount)
    		);

        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response: " + response);
            }

            if (response.body() == null) {
                throw new IOException("Empty response body");
            }

            String responseBody = response.body().string();
            JsonNode json = mapper.readTree(responseBody);

            JsonNode resultNode = json.get("result");
            if (resultNode == null || resultNode.isNull()) {
                throw new IOException("Invalid API response: 'result' field missing in response: " + responseBody);
            }

            return resultNode.asDouble();
        }
    }

    private static void showCurrencyOptions(String[] currencies) {
        for (int i = 0; i < currencies.length; i++) {
            System.out.printf("%d. %s%n", i + 1, currencies[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] currencies = {"USD", "EUR", "INR", "GBP", "JPY", "AUD", "CAD", "CHF", "CNY", "ZAR"};

        try {
            System.out.println("Select source currency:");
            showCurrencyOptions(currencies);
            int sourceIndex = scanner.nextInt() - 1;

            System.out.println("Select target currency:");
            showCurrencyOptions(currencies);
            int targetIndex = scanner.nextInt() - 1;

            if (sourceIndex < 0 || sourceIndex >= currencies.length ||
                targetIndex < 0 || targetIndex >= currencies.length) 
            {
                System.out.println("Invalid currency selection.");
                return;
            }

            System.out.print("Enter amount to convert: ");
            double amount = scanner.nextDouble();

            String fromCurrency = currencies[sourceIndex];
            String toCurrency = currencies[targetIndex];

            double result = convert(fromCurrency, toCurrency, amount);
            System.out.printf("%.2f %s = %.2f %s%n", amount, fromCurrency, result, toCurrency);

       } 
        catch (IOException e) 
         {
            System.err.println("Error during conversion: " + e.getMessage());
         } 
        catch (Exception e) 
         {
            System.err.println("Unexpected error: " + e.getMessage());
         } 
        finally 
         {
            scanner.close();
         }
    }
}
