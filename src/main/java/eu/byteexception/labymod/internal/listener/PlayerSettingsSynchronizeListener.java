package eu.byteexception.labymod.internal.listener;

import eu.byteexception.labymod.OPSuchtLabyAddon;
import eu.byteexception.labymod.internal.events.PlayerSettingsSynchronizeEvent;
import eu.byteexception.labymod.settings.PlayerSettings;
import lombok.AllArgsConstructor;
import net.labymod.api.event.Subscribe;

import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
public class PlayerSettingsSynchronizeListener {

    private final OPSuchtLabyAddon addon;

    @Subscribe
    public void onPlayerSettingsSynchronize(PlayerSettingsSynchronizeEvent event) {
        PlayerSettings.setPlayerSettings(event.getPlayerSettings());

        this.addon.getModules()
                .stream()
                .filter(labyModule -> Objects.nonNull(labyModule.getListener()))
                .filter(labyModule -> event.getPlayerSettings().get(labyModule.getSettingName()).getAsBoolean())
                .collect(Collectors.toMap(labyModule -> labyModule, labyModule -> {
                    try {
                        return labyModule.getListener().newInstance();
                    } catch (InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return 0;
                })).forEach((labyModule, listener) -> {
            if (listener instanceof Integer) return;

            this.addon.getModules().remove(labyModule);
            this.addon.getApi().getEventService().registerListener(listener);
            this.addon.getLogger().info("Registering listener " + listener.getClass().getSimpleName());
        });
    }
}