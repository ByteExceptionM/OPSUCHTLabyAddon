package eu.byteexception.labymod.listener.labymod;

import eu.byteexception.labymod.OPSuchtLabyAddon;
import lombok.AllArgsConstructor;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageModifyEvent;
import net.labymod.utils.ModColor;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.ClickEvent.Action;
import net.minecraft.util.text.event.HoverEvent;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class MessageModifyListener {

    private final OPSuchtLabyAddon addon;

    @Subscribe
    public void onMessageModify(MessageModifyEvent event) {
        if (!this.addon.getAddonSettings().getClickableNicks() || !this.addon.getOpSuchtLabyServer().getConnected()) return;

        ITextComponent component = event.getComponent();

        if (!component.getString().contains("|") || !component.getString().contains("~")) return;

        List<ITextComponent> componentSiblings = component.getSiblings();

        if (componentSiblings.isEmpty()) return;

        ITextComponent playerNameComponent = componentSiblings.stream().filter(textComponent -> textComponent.getString().startsWith("~")).findFirst().orElse(null);

        if (playerNameComponent == null) return;

        String playerName = playerNameComponent.getString();

        List<ITextComponent> playerNameComponentSiblings = new LinkedList<>(Collections.singletonList(playerNameComponent));

        if (playerNameComponent.getString().equals("~")) {
            StringBuilder playerNameBuilder = new StringBuilder();

            for (int i = componentSiblings.indexOf(playerNameComponent) + 1; i < componentSiblings.size(); i++) {
                ITextComponent textComponent = componentSiblings.get(i);

                if (textComponent.getString().startsWith(" ")) break;

                playerNameBuilder.append(textComponent.getString());

                playerNameComponentSiblings.add(textComponent);
            }

            playerName = playerNameBuilder.substring(0);
        }

        String finalPlayerName = playerName;

        playerNameComponentSiblings.forEach(textComponent ->
                component.getSiblings().set(componentSiblings.indexOf(textComponent), this.applyStyle(textComponent, finalPlayerName)));

        event.setComponent(component);
    }

    private ITextComponent applyStyle(ITextComponent textComponent, String playerName) {
        return TextComponentUtils.func_240648_a_(textComponent.copyRaw(), textComponent.getStyle().setClickEvent(null).setHoverEvent(null).mergeStyle(
                textComponent.getStyle()
                        .setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/realname " + playerName))
                        .setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new StringTextComponent(ModColor.GREEN + "Klicke, für den richtigen Namen")))
        ));
    }
}