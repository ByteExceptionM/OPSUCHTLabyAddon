package eu.byteexception.labymod.listener.labymod;

import com.google.gson.JsonObject;
import eu.byteexception.labymod.settings.PlayerSettings;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.network.server.LoginServerEvent;

public class LoginServerListener {

    @Subscribe
    public void onServerLogin(LoginServerEvent event) {
        PlayerSettings.setPlayerSettings(new JsonObject());
    }

}