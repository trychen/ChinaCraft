package unstudio.chinacraft.item.jade;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import unstudio.chinacraft.event.jade.JadePinkCDReduceEvent;

/**
 * Created by trych on 2016/1/9.
 */
public class JadePinkSystem {
    private HashMap<UUID, Integer> time = new HashMap<UUID, Integer>();

    public int getPlayerCD(UUID uuid) {
        if (time.containsKey(uuid))
            return time.get(uuid);
        time.put(uuid, 0);
        return 0;
    }

    public void init() {
        new Timer().schedule(new ReduceTimeCDTask(), 100, 100);
    }

    public boolean hasCD(UUID uuid) {
        if (time.containsKey(uuid))
            return true;
        return false;
    }

    class ReduceTimeCDTask extends TimerTask {
        @Override
        public void run() {
            for (UUID uuid : JadePinkSystem.this.time.keySet()) {
                if (JadePinkSystem.this.time.get(uuid) > 0) {
                    JadePinkCDReduceEvent e = new JadePinkCDReduceEvent(uuid, JadePinkSystem.this.time.get(uuid));
                    if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(e))
                        continue;
                    JadePinkSystem.this.time.put(uuid, JadePinkSystem.this.time.get(uuid) - 1);
                } else {
                    JadePinkCDReduceEvent e = new JadePinkCDReduceEvent(uuid, 0);
                    if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(e))
                        continue;
                    JadePinkSystem.this.time.remove(uuid);
                }
            }
        }
    }
}
