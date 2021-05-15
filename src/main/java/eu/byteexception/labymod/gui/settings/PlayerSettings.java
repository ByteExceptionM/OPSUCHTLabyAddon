package eu.byteexception.labymod.gui.settings;

import com.google.gson.JsonObject;

public class PlayerSettings {

    public static JsonObject playerSettings = new JsonObject();

    public static Boolean getBooleanValue(String key) {
        return playerSettings.has(key) && playerSettings.get(key).getAsBoolean();
    }

}