package ralseiii.skyfabric.utils.api.auctions;
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class LowestBin {
    static AtomicReference<Map<String, Long>> priceMapReference = new AtomicReference<>();
    public static void update() {
        try {
            // use skytils' api to fetch lowest bin
            var lowestBinApi = new URL("https://api.skytils.gg/api/auctions/lowestbins");
            var connection = (HttpURLConnection) lowestBinApi.openConnection();
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == 200) {
                var s = new Scanner(connection.getInputStream());
                var b = new StringBuilder();
                while (s.hasNextLine()) {
                    b.append(s.nextLine()).append("\n");
                }
                s.close();
                var items = new Gson().fromJson(b.toString(), JsonObject.class);
                Map<String, Long> priceMap = new HashMap<>();
                for (var entry : items.entrySet()) {
                    priceMap.put(entry.getKey(), entry.getValue().getAsLong());
                }
                priceMapReference.set(priceMap);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Long get(String id) {
        if (priceMapReference.get() == null) return 0L;
        return priceMapReference.get().getOrDefault(id, 0L);
    }

    public static Boolean isAvailable(String id) {
        if (priceMapReference.get() == null) return false;
        return priceMapReference.get().containsKey(id);
    }
}
