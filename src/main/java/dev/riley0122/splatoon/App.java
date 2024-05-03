package dev.riley0122.splatoon;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class App extends JavaPlugin implements Listener
{
    public static List<Weapon> weapons = new ArrayList<Weapon>();

    @Override
    public void onEnable() {
        getLogger().info("Hello, SpigotMC!");
        // Create all weapons
        Weapon splattershotJr = new Weapon("Splattershot Jr.", "splattershot_jr", WeaponType.SHOOTER, 12, 0.72f, 1/3, 28, 2, 0);
        weapons.add(splattershotJr);
        Weapon Splattershot = new Weapon("Splattershot", "splattershot", WeaponType.SHOOTER, 10, 0.72f, 1/3, 35, 2, 0);
        weapons.add(Splattershot);
        Weapon CarbonRoller = new Weapon("Carbon Roller", "carbon_roller", WeaponType.ROLLER, 1, 1.44f, 1/3, 70, 3, 44);
        weapons.add(CarbonRoller);
        Weapon SplatRoller = new Weapon("Splat Roller", "splat_roller", WeaponType.ROLLER, 1, 1.2f, 1/3, 125, 3, 52);
        weapons.add(SplatRoller);

        // Register all commands
        this.getCommand("getWeapon").setExecutor(new CommandGetWeapon());
        this.getCommand("listWeapons").setExecutor(new CommandListWeapons());

        // Register event handler
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("bleh");
    }

    public Team getPlayerTeam(Player p) {
        Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
        Team T = null;
        for (Team team : sb.getTeams()) {
            if (team.hasEntry(p.getDisplayName())) {
                T = team;
            }
        }
        return T;
    }

    public Material getMaterialFromColourName(String colourName) {
        switch (colourName) {
            case "yellow":
                return Material.YELLOW_WOOL;
            case "purple":
                return Material.PURPLE_WOOL;
            case "pink":
                return Material.MAGENTA_WOOL;
            case "green":
                return Material.LIME_WOOL;
        }
        return Material.BLUE_WOOL;
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Snowball) {
            switch (event.getEntity().getCustomName()) {
                case "splattershot_projectile":
                    if (event.getHitBlock() == null || event.getHitBlock().getType() == Material.BLACKSTONE) {
                        return;
                    }
                    Player shooter = (Player) event.getEntity().getShooter();
                    Team shooterTeam = getPlayerTeam(shooter);
                    String colour = "";
                    if (shooterTeam != null) {
                        colour = shooterTeam.getName();
                    }
                    event.getHitBlock().setType(getMaterialFromColourName(colour));
                break;
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction().toString().contains("RIGHT_CLICK")) {
            // Weapons and their custom model indecies:
            //  | splattershot_jr : 1
            //  | splattershot    : 2
            //  | carbon_roller   : 3
            //  | splat_roller    : 4
            //  ;
            if (event.getItem().equals(null) || event.getItem().getType() != Material.STICK) {
                return;
            }

            switch (event.getItem().getItemMeta().getCustomModelData()) {
                case 1:
                case 2:
                    event.getPlayer().sendMessage("§d[Splatoon]§r You tried to fire the Splattershot");
                    Snowball splat = event.getPlayer().getWorld().spawn(event.getPlayer().getEyeLocation(), Snowball.class);
                    splat.setCustomNameVisible(false);
                    splat.setCustomName("splattershot_projectile");
                    splat.setShooter(event.getPlayer());
                    splat.setVelocity(event.getPlayer().getLocation().getDirection().multiply(1.5));
                    break;
                case 3:
                    event.getPlayer().sendMessage("§d[Splatoon]§r You tried to roll the Carbon Roller");
                    break;
                case 4:
                    event.getPlayer().sendMessage("§d[Splatoon]§r You tried to roll the Splat Rolelr");
                    break;
                default:
                    event.getPlayer().sendMessage("§d[Splatoon]§r Something went wrong!");
                    break;
            }
        }
    }
}
