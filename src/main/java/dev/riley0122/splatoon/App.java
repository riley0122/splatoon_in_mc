package dev.riley0122.splatoon;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin
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
        Weapon SplatRoller = new Weapon("Splat Roller", "carbon_roller", WeaponType.ROLLER, 1, 1.2f, 1/3, 125, 3, 52);
        weapons.add(SplatRoller);

        // Register all commands
        this.getCommand("getWeapon").setExecutor(new CommandGetWeapon());
        this.getCommand("listWeapons").setExecutor(new CommandListWeapons());
    }

    @Override
    public void onDisable() {
        getLogger().info("bleh");
    }
}
