package eu.byteexception.labymod.listener.labymod;

import eu.byteexception.labymod.gui.modules.FlyModule.FlySetting;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.network.ServerMessageEvent;

public class FlyModuleUpdateListener {

    @Subscribe
    public void onServerMessage(ServerMessageEvent event) {
        if (!event.getMessageKey().equals("opsucht_fly_mode")) return;

        FlySetting.flyMode = event.getServerMessage().getAsJsonObject().get("value").getAsBoolean();
    }
}