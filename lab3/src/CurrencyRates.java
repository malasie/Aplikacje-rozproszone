import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

class CurrencyRates {
    private static final Object lock = new Object();

    public static void main(String[] args) {
        printRate("Euro" ,"EUR");
        printRate("Dolar amerykański", "USD");
        printRate("Frank szwajcarski", "CHF");

    }

    private static void printRate(String currencyFullName, String currencyCode) {

        try {
            String url = "http://api.nbp.pl/api/exchangerates/tables/A?format=json";
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");

            Scanner scanner = new Scanner(connection.getInputStream());
            StringBuilder response = new StringBuilder();

            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }

            synchronized (lock) {
                System.out.println(getRate(response.toString(), currencyFullName, currencyCode));
            }

            connection.disconnect();
        } catch (Exception ignored) {
        }
    }

    private static String getRate(String json, String currencyFullName, String currencyCode) {
        JSONArray jsonArray = new JSONArray(json);
        JSONArray ratesArray = jsonArray.getJSONObject(0).getJSONArray("rates");

        for (int i = 0; i < ratesArray.length(); i++) {
            JSONObject rateObject = ratesArray.getJSONObject(i);
            if (rateObject.getString("code").equals(currencyCode)) {
                BigDecimal exchangeRate = rateObject.getBigDecimal("mid");
                return currencyFullName + " ("+currencyCode+ "): " + exchangeRate.toPlainString();
            }
        }

        return "Brak danych";
    }
}
