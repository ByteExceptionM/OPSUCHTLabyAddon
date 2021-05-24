package eu.byteexception.labymod.settings;

import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;

public class PlayerSettings {

    @Getter @Setter
    private static JsonObject playerSettings = new JsonObject();

    @Getter @Setter
    private static Boolean flyMode = false;

    @Getter @Setter
    private static Boolean godMode = false;

    @Getter @Setter
    private static Boolean vanishMode = false;

    public static Boolean getBooleanValue(String key) {
        return playerSettings.has(key) && playerSettings.get(key).getAsBoolean();
    }
}