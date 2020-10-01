package Ten.TenUtil;

import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class Hyvaksy implements CommandExecutor
{
    private Main plugin;
    private Eventer events;

    public Hyvaksy(final Main main, final Eventer even) {
        this.plugin = main;
        this.events = even;
    }

    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] strings) {
        if (commandSender instanceof Player) {
            final Player player = (Player)commandSender;
            if (strings.length > 0 && strings[0].equalsIgnoreCase("order66")) {
                final List users = this.plugin.getConfig().getStringList("accepted");
                users.remove(player.getUniqueId().toString());
                this.plugin.getConfig().set("accepted", (Object)users);
                this.plugin.saveConfig();
                player.sendMessage("Olet poistanut itsesi s\u00e4\u00e4nt\u00f6jen hyv\u00e4ksyneist\u00e4.");
                return true;
            }
            if (this.plugin.getConfig().getStringList("accepted").contains(player.getUniqueId().toString())) {
                player.sendMessage(ChatColor.RED + "Olet jo hyv\u00e4ksynyt s\u00e4\u00e4nn\u00f6t." + ChatColor.YELLOW + " Tervetuloa ;)");
            }
            else {
                final List users = this.plugin.getConfig().getStringList("accepted");
                users.add(player.getUniqueId().toString());
                this.plugin.getConfig().set("accepted", (Object)users);
                this.plugin.saveConfig();
                this.events.refreshUsersList();
                player.sendMessage(ChatColor.GREEN + "Kiitos kun hyv\u00e4ksyit s\u00e4\u00e4nn\u00f6t, hauskoja pelihetki\u00e4 ;)");
                this.plugin.getServer().broadcastMessage("Tervetuloa " + ChatColor.AQUA + player.getName() + ChatColor.WHITE + "!");
            }
        }
        else {
            this.plugin.getLogger().info("Et voi hyv\u00e4ksy\u00e4 s\u00e4\u00e4nt\u00f6j\u00e4 komentokuutiolla/konsolista.");
        }
        return true;
    }
}