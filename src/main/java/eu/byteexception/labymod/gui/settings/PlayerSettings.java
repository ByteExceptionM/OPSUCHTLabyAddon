package eu.byteexception.labymod.gui.settings;

import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;

public class PlayerSettings {

    @Getter
    @Setter
    private static JsonObject playerSettings = new JsonObject();

    public static Boolean getBooleanValue(String key) {
        return playerSettings.has(key) && playerSettings.get(key).getAsBoolean();
    }
}