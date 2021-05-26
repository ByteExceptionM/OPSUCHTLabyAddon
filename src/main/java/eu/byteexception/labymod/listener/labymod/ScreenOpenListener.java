package eu.byteexception.labymod.listener.labymod;

import com.google.gson.JsonObject;
import eu.byteexception.labymod.settings.PlayerSettings;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.gui.screen.ScreenOpenEvent;
import net.minecraft.client.gui.screen.ConnectingScreen;

public class ScreenOpenListener {

    @Subscribe
    public void onServerLogin(ScreenOpenEvent event) {
        if (!(event.getScreen() instanceof ConnectingScreen)) return;

        PlayerSettings.setPlayerSettings(new JsonObject());
    }

}