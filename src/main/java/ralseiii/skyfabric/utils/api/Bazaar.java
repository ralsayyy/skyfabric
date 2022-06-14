package ralseiii.skyfabric.utils.api;
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class Bazaar {
    static AtomicReference<Map<String, Long>> bazaarSellPriceMapReference = new AtomicReference<>();
    static AtomicReference<Map<String, Long>> bazaarBuyPriceMapReference = new AtomicReference<>();
    public static void update() {
        try {
            var bazaarApi = new URL("https://api.hypixel.net/skyblock/bazaar");
            var connection = (HttpURLConnection) bazaarApi.openConnection();
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == 200) {
                Map<String, Long> bazaarSellPriceMap = new HashMap<>();
                Map<String, Long> bazaarBuyPriceMap = new HashMap<>();
                var s = new Scanner(connection.getInputStream());
                var b = new StringBuilder();
                while (s.hasNextLine()) {
                    b.append(s.nextLine()).append("\n");
                }
                s.close();
                var products = (new Gson().fromJson(b.toString(), JsonObject.class)).getAsJsonObject("products");
                for (var entry : products.entrySet()) {
                    // convert map.entry back to JsonObject
                    var o = entry.getValue().getAsJsonObject();
                    var productInfo = o.getAsJsonObject("quick_status");
                    var id = productInfo.get("productId").getAsString();
                    bazaarSellPriceMap.put(id, productInfo.get("sellPrice").getAsLong());
                    bazaarBuyPriceMap.put(id, productInfo.get("buyPrice").getAsLong());
                }
                bazaarSellPriceMapReference.set(bazaarSellPriceMap);
                bazaarBuyPriceMapReference.set(bazaarBuyPriceMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Long getSellPriceForId(String id) {
            return bazaarSellPriceMapReference.get().getOrDefault(id, 0L);
    }
    public static Long getBuyPriceForId(String id) {
            return bazaarBuyPriceMapReference.get().getOrDefault(id, 0L);
    }
    public static Boolean isBazaarItem(String id) {
       return bazaarSellPriceMapReference.get().containsKey(id);
    }
}
