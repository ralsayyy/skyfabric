package ralseiii.skyfabric.utils.api;

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
