package dev.riley0122.splatoon;

import org.bukkit.Material;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandGetWeapon implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage("§d[Splatoon]§r You need to provide exactly 1 arguement! You provided " + args.length + " arguements!");
            }
            return false;
        }

        if (sender instanceof Player) {
            Player player = (Player) sender;

            ItemStack weapon = new ItemStack(Material.STICK);
            weapon.setAmount(1);

            for (int i = 0; i < App.weapons.size(); i++) {
                if (App.weapons.get(i).id.trim().toLowerCase().equals(args[0].trim().toLowerCase())) {
                    Weapon W = App.weapons.get(i);
                    ItemMeta meta = weapon.getItemMeta();

                    meta.setDisplayName(W.name);
                    meta.setCustomModelData(i + 1);

                    weapon.setItemMeta(meta);

                    player.getInventory().clear();
                    player.getInventory().setItemInMainHand(weapon);
                    return true;
                }
            }

            player.sendMessage("§d[Splatoon]§r "+ args[0] + " is not a valid weapon id! list all weapons with /listweapons");
        }

        return false;
    }
}
