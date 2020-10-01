package Ten.TenUtil;

import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerJoinEvent;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.event.Listener;

public class Eventer implements Listener
{
    private Main plugin;
    List<String> users;

    public Eventer(final Main plugin2) {
        this.users = new ArrayList<>();
        this.plugin = plugin2;
        this.users = this.plugin.getTConfig().getStringList("accepted");
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(final PlayerJoinEvent e) {
        final Player pl = e.getPlayer();
        if (this.users.contains(e.getPlayer().getUniqueId().toString())) {
            this.plugin.getServer().broadcastMessage("Tervetuloa " + ChatColor.AQUA + pl.getName() + ChatColor.WHITE + "!");
            e.setJoinMessage("");
        }
        else {
            final TextComponent message = new TextComponent("Paina tasta avataksesi saannot");
            message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://docs.google.com/document/d/1MH_QIxVtwV2wcsKPCp2Gor4_pNYEf5h-qsa3fvfVDVs"));
            message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Avaa saannot selaimessa.").create()));
            message.setUnderlined(Boolean.valueOf(true));
            pl.spigot().sendMessage(message);
            pl.sendMessage("Sinun " + ChatColor.RED + "on luettava saannot " + ChatColor.WHITE + "ennen kuin paaset pelaamaan.");
            e.setJoinMessage(ChatColor.YELLOW + pl.getName() + ChatColor.WHITE + " liittyi palvelimelle, muttei ole viela hyvaksynyt saantoja.");
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerMove(final PlayerMoveEvent e) {
        if (!this.users.contains(e.getPlayer().getUniqueId().toString())) {
            e.setCancelled(true);
        }
    }

    public void refreshUsersList() {
        this.users = this.plugin.getTConfig().getStringList("accepted");
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerCommandPreprocess(final PlayerCommandPreprocessEvent e) {
        if (!this.users.contains(e.getPlayer().getUniqueId().toString())) {
            final Player pl = e.getPlayer();
            final String cmd = e.getMessage();
            if (cmd.equalsIgnoreCase("/help")) {
                e.setCancelled(true);
                final TextComponent message = new TextComponent("Paina t\u00e4st\u00e4 avataksesi s\u00e4\u00e4nn\u00f6t");
                message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://docs.google.com/document/d/1MH_QIxVtwV2wcsKPCp2Gor4_pNYEf5h-qsa3fvfVDVs"));
                message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Avaa s\u00e4\u00e4nn\u00f6t selaimessa.").create()));
                message.setUnderlined(Boolean.valueOf(true));
                pl.spigot().sendMessage(message);
                pl.sendMessage("Sinun " + ChatColor.RED + "on luettava s\u00e4\u00e4nn\u00f6t " + ChatColor.WHITE + "ennen kuin p\u00e4\u00e4set pelaamaan.");
            }
            if (!cmd.equalsIgnoreCase("/hyvaksy")) {
                e.setCancelled(true);
            }
        }
    }
}