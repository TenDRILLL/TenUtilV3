package Ten.TenUtil;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.event.Listener;

public class Eventer implements Listener
{
    private Main plugin;
    List<String> users;
    FileConfiguration messages;
    FileConfiguration config;

    public Eventer(final Main plugin2) {
        this.users = new ArrayList<>();
        this.plugin = plugin2;
        this.config = this.plugin.getTConfig();
        this.users = this.config.getStringList("accepted");
        this.messages = this.plugin.getMessages();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(final PlayerJoinEvent e) {
        final Player pl = e.getPlayer();
        if (!this.users.contains(e.getPlayer().getUniqueId().toString())) {
            final TextComponent message = new TextComponent(this.messages.getString("CLICK_TO_OPEN"));
            message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://docs.google.com/document/d/1MH_QIxVtwV2wcsKPCp2Gor4_pNYEf5h-qsa3fvfVDVs"));
            message.setUnderlined(true);
            pl.spigot().sendMessage(message);
            pl.sendMessage(this.messages.getString("NOT_ACCEPTED_PRIVATE"));
            e.setJoinMessage(this.messages.getString("NOT_ACCEPTED_PUBLIC"));
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerMove(final PlayerMoveEvent e) {
        if (!this.users.contains(e.getPlayer().getUniqueId().toString())) {
            e.setCancelled(true);
        }
    }

    public void refreshUsersList() {
        this.users = this.config.getStringList("accepted");
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerCommandPreprocess(final PlayerCommandPreprocessEvent e) {
        if (!this.users.contains(e.getPlayer().getUniqueId().toString())) {
            final Player pl = e.getPlayer();
            final String cmd = e.getMessage();
            if (cmd.equalsIgnoreCase("/help")) {
                e.setCancelled(true);
                final TextComponent message = new TextComponent(this.messages.getString("CLICK_TO_OPEN"));
                message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, this.config.getString("RULES_LINK")));
                message.setUnderlined(Boolean.valueOf(true));
                pl.spigot().sendMessage(message);
                pl.sendMessage(this.messages.getString("NOT_ACCEPTED_PRIVATE"));
            }
            if (!cmd.equalsIgnoreCase("/hyvaksy") && !cmd.equalsIgnoreCase("/saannot")) {
                e.setCancelled(true);
            }
        }
    }
}