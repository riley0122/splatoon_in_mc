package dev.riley0122.splatoon;

import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class CommandListWeapons implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        player.sendMessage("§d[Splatoon]§r List of weapons:");

        for (int i = 0; i < App.weapons.size(); i++) {
            player.sendMessage("§d[Splatoon]§r " + App.weapons.get(i).name + " - " + App.weapons.get(i).id);
        }
        return true;
    }
}