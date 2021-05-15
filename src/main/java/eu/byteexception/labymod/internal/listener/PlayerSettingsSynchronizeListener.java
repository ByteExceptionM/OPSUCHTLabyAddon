package eu.byteexception.labymod.internal.listener;

import eu.byteexception.labymod.gui.settings.PlayerSettings;
import eu.byteexception.labymod.internal.events.PlayerSettingsSynchronizeEvent;
import net.labymod.api.event.Subscribe;

public class PlayerSettingsSynchronizeListener {

    @Subscribe
    public void onPlayerSettingsSynchronize(PlayerSettingsSynchronizeEvent event) {
        PlayerSettings.playerSettings = event.getPlayerSettings();
    }
}