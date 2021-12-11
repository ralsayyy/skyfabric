package ralseiii.skyfabric.utils.api;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Bazaar {
    static Map<String, Long> bazaarSellPriceMap = new HashMap<>();
    static Map<String, Long> bazaarBuyPriceMap = new HashMap<>();
    public static void update() {
        try {
            URL bazaarApi = new URL("https://api.hypixel.net/skyblock/bazaar");
            HttpURLConnection connection = (HttpURLConnection) bazaarApi.openConnection();
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == 200) {
                Scanner s = new Scanner(connection.getInputStream());
                StringBuilder b = new StringBuilder();
                while (s.hasNextLine()) {
                    b.append(s.nextLine() + "\n");
                }
                s.close();
                JsonObject products = (new Gson().fromJson(b.toString(), JsonObject.class)).getAsJsonObject("products");
                for (Map.Entry<String, JsonElement> entry : products.entrySet()) {
                    // convert map.entry back to JsonObject
                    JsonObject o = entry.getValue().getAsJsonObject();
                    JsonObject productInfo = o.getAsJsonObject("quick_status");
                    String id = productInfo.get("productId").getAsString();
                    bazaarSellPriceMap.put(id, productInfo.get("sellPrice").getAsLong());
                    bazaarBuyPriceMap.put(id, productInfo.get("buyPrice").getAsLong());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Long getSellPriceForId(String id) {
            return bazaarSellPriceMap.getOrDefault(id, 0L);
    }
    public static Long getBuyPriceForId(String id) {
            return bazaarBuyPriceMap.getOrDefault(id, 0L);
    }
    public static Boolean isBazaarItem(String id) {
       return bazaarSellPriceMap.containsKey(id);
    }
}
