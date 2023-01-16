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
                player.sendMessage("Removed u from rule accepted list.");
                return true;
            }
            if (this.plugin.getConfig().getStringList("accepted").contains(player.getUniqueId().toString())) {
                player.sendMessage(ChatColor.RED + "Olet jo hyväksynyt säännöt." + ChatColor.YELLOW + " Tervetuloa ;)");
            } else {
                final List users = this.plugin.getConfig().getStringList("accepted");
                users.add(player.getUniqueId().toString());
                this.plugin.getConfig().set("accepted", (Object)users);
                this.plugin.saveConfig();
                this.events.refreshUsersList();
                player.sendMessage(ChatColor.GREEN + "Kiitos kun hyväksyit säännöt, hauskoja pelihetkiä ;)");
                this.plugin.getServer().broadcastMessage("Tervetuloa " + ChatColor.AQUA + player.getName() + ChatColor.WHITE + "!");
            }
        } else {
            this.plugin.getLogger().warning("Hyväsky was attempted to be used without a user.");
        }
        return true;
    }
}