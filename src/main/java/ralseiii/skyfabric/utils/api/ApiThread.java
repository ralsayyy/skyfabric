package ralseiii.skyfabric.utils.api;
/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */
import ralseiii.skyfabric.utils.api.auctions.LowestBin;

public class ApiThread extends Thread {
    public final Short notify = 0;
    public void run() {
        while (true) {
            synchronized (notify) {
                try {
                    notify.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Bazaar.update();
                LowestBin.update();
            }
        }
    }
}
