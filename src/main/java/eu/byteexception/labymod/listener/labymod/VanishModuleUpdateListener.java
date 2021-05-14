package eu.byteexception.labymod.listener.labymod;

import eu.byteexception.labymod.OPSuchtLabyModAddon;
import eu.byteexception.labymod.gui.modules.VanishModule.VanishSetting;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.network.ServerMessageEvent;

public class VanishModuleUpdateListener {

    private final OPSuchtLabyModAddon addon;

    public VanishModuleUpdateListener(OPSuchtLabyModAddon addon) {
        this.addon = addon;
    }

    @Subscribe
    public void onServerMessage(ServerMessageEvent event) {
        if (!event.getMessageKey().equals("opsucht_vanish_mode")) return;

        VanishSetting.vanishMode = event.getServerMessage().getAsJsonObject().get("value").getAsBoolean();
    }
}