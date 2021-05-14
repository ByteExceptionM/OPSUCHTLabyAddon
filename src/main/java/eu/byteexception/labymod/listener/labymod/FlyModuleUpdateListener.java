package eu.byteexception.labymod.listener.labymod;

import eu.byteexception.labymod.OPSuchtLabyModAddon;
import eu.byteexception.labymod.gui.modules.FlyModule.FlySetting;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.network.ServerMessageEvent;

public class FlyModuleUpdateListener {

    private final OPSuchtLabyModAddon addon;

    public FlyModuleUpdateListener(OPSuchtLabyModAddon addon) {
        this.addon = addon;
    }

    @Subscribe
    public void onServerMessage(ServerMessageEvent event) {
        if (!event.getMessageKey().equals("opsucht_fly_mode")) return;

        FlySetting.flyMode = event.getServerMessage().getAsJsonObject().get("value").getAsBoolean();
    }
}