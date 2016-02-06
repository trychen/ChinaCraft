package unstudio.chinacraft.event.jade;


import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;

import java.util.UUID;


@Cancelable
public class JadePinkCDReduceEvent extends Event{

    public final UUID playerUUID;
    public final int CD;

    public JadePinkCDReduceEvent(UUID playerUUID, int CD) {
        this.playerUUID = playerUUID;
        this.CD = CD;
    }

    public int getNewCD(){
        return CD-1;
    }
    public int getOldCD(){
        return CD;
    }
}
