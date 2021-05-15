package eu.byteexception.labymod.listener.server;

import eu.byteexception.labymod.OPSuchtLabyAddon;
import eu.byteexception.labymod.gui.settings.PlayerSettings;
import lombok.AllArgsConstructor;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.network.ServerMessageEvent;

@AllArgsConstructor
public class SettingsSynchronisationListener {

    private final OPSuchtLabyAddon addon;

    @Subscribe
    public void onServerMessage(ServerMessageEvent event) {
        if (!event.getMessageKey().equals("opsucht_settings")) return;

        PlayerSettings.playerSettings = event.getServerMessage().getAsJsonObject();
    }
}