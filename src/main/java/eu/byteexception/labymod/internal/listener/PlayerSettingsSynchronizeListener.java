package eu.byteexception.labymod.internal.listener;

import eu.byteexception.labymod.OPSuchtLabyAddon;
import eu.byteexception.labymod.gui.settings.PlayerSettings;
import eu.byteexception.labymod.internal.events.PlayerSettingsSynchronizeEvent;
import lombok.AllArgsConstructor;
import net.labymod.api.event.Subscribe;

@AllArgsConstructor
public class PlayerSettingsSynchronizeListener {

    private final OPSuchtLabyAddon addon;

    @Subscribe
    public void onPlayerSettingsSynchronize(PlayerSettingsSynchronizeEvent event) {
        PlayerSettings.playerSettings = event.getPlayerSettings();
    }
}