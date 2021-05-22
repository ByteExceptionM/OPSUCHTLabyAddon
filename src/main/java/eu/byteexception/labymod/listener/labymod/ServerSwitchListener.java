package eu.byteexception.labymod.listener.labymod;

import com.google.gson.JsonObject;
import eu.byteexception.labymod.OPSuchtLabyAddon;
import eu.byteexception.labymod.gui.settings.PlayerSettings;
import lombok.AllArgsConstructor;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.network.server.ServerSwitchEvent;

@AllArgsConstructor
public class ServerSwitchListener {

    private final OPSuchtLabyAddon addon;

    @Subscribe
    public void onServerSwitch(ServerSwitchEvent event) {
        if (!this.addon.getOpSuchtLabyServer().getConnected()) return;

        PlayerSettings.setPlayerSettings(new JsonObject());
    }
}