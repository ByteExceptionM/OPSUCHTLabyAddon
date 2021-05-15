package eu.byteexception.labymod.internal.listener;

import eu.byteexception.labymod.gui.modules.GodModule.GodModeSettings;
import eu.byteexception.labymod.internal.events.GodModeUpdateEvent;
import net.labymod.api.event.Subscribe;

public class GodModeUpdateListener {

    @Subscribe
    public void onGodModeUpdate(GodModeUpdateEvent event) {
        GodModeSettings.godMode = event.getState();
    }
}