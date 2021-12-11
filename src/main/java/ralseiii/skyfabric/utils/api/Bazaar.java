package ralseiii.skyfabric.utils.api;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Bazaar {
    static Map<String, Double> bazaarSellPriceMap = new HashMap<>();
    static Map<String, Double> bazaarBuyPriceMap = new HashMap<>();
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
                    Double sellPrice = productInfo.get("sellPrice").getAsDouble();
                    Double buyPrice = productInfo.get("buyPrice").getAsDouble();
                    bazaarSellPriceMap.put(id, sellPrice);
                    bazaarBuyPriceMap.put(id, buyPrice);
                }
            }
        } catch (MalformedURLException e) {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client != null)
                client.player.sendMessage(Text.of("[skyfabric] failed to get bazaar data (java.net.MalformedURLException)"), false);
        } catch (IOException e) {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client != null && client.player != null)
                client.player.sendMessage(Text.of("[skyfabric] failed to get bazaar data (java.io.IOException)"), false);
        }
    }
    public static Double getSellPriceForId(String id) {
        if (bazaarSellPriceMap != null)
            return bazaarSellPriceMap.getOrDefault(id, 0d);
        else
            return 0d;
    }
    public static Double getBuyPriceForId(String id) {
        if (bazaarBuyPriceMap != null)
            return bazaarBuyPriceMap.getOrDefault(id, 0d);
        else
            return 0d;
    }
    public static Boolean isBazaarItem(String id) {
       return bazaarSellPriceMap.containsKey(id);
    }
}
