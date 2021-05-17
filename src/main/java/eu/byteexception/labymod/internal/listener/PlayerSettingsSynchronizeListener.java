package eu.byteexception.labymod.internal.listener;

import eu.byteexception.labymod.OPSuchtLabyAddon;
import eu.byteexception.labymod.gui.settings.PlayerSettings;
import eu.byteexception.labymod.internal.events.PlayerSettingsSynchronizeEvent;
import lombok.AllArgsConstructor;
import net.labymod.api.event.Subscribe;

import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
public class PlayerSettingsSynchronizeListener {

    private final OPSuchtLabyAddon addon;

    @Subscribe
    public void onPlayerSettingsSynchronize(PlayerSettingsSynchronizeEvent event) {
        PlayerSettings.playerSettings = event.getPlayerSettings();

        this.addon.getModuleListener().stream().filter(listenerClazz -> this.addon.getModules().stream().filter(labyModule -> Objects.nonNull(labyModule.getListenerName()))
                .anyMatch(labyModule -> labyModule.getListenerName().equals(listenerClazz) && event.getPlayerSettings().get(labyModule.getSettingName()).getAsBoolean())).map(clazz -> {
            try {
                return Class.forName(clazz, true, Thread.currentThread().getContextClassLoader()).newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList()).forEach(listener -> {
            this.addon.getModuleListener().remove(listener.getClass().getCanonicalName());
            this.addon.getApi().getEventService().registerListener(listener);
            this.addon.getLogger().info("Registering listener " + listener.getClass().getSimpleName());
        });
    }
}