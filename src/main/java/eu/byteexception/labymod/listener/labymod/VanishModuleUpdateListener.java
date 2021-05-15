package eu.byteexception.labymod.listener.labymod;

import eu.byteexception.labymod.OPSuchtLabyAddon;
import eu.byteexception.labymod.gui.modules.VanishModule.VanishSetting;
import lombok.AllArgsConstructor;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.network.ServerMessageEvent;

@AllArgsConstructor
public class VanishModuleUpdateListener {

    private final OPSuchtLabyAddon addon;

    @Subscribe
    public void onServerMessage(ServerMessageEvent event) {
        if (!event.getMessageKey().equals("opsucht_vanish_mode")) return;

        VanishSetting.vanishMode = event.getServerMessage().getAsJsonObject().get("value").getAsBoolean();
    }
}