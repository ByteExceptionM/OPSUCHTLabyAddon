package eu.byteexception.labymod.listener.labymod;

import eu.byteexception.labymod.OPSuchtLabyAddon;
import eu.byteexception.labymod.gui.modules.FlyModule.FlySetting;
import lombok.AllArgsConstructor;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.network.ServerMessageEvent;

@AllArgsConstructor
public class FlyModuleUpdateListener {

    private final OPSuchtLabyAddon addon;

    @Subscribe
    public void onServerMessage(ServerMessageEvent event) {
        if (!event.getMessageKey().equals("opsucht_fly_mode")) return;

        FlySetting.flyMode = event.getServerMessage().getAsJsonObject().get("value").getAsBoolean();
    }
}