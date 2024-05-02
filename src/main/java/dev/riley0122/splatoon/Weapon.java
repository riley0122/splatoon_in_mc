package dev.riley0122.splatoon;

import org.bukkit.Bukkit;

public class Weapon {
    public WeaponType type;

    public String name;
    public String id;

    public int sps;             // Shots per second
    public float playerSpeed;   // What the players speed gets set to when using
    public float cooldown;        // The cooldown in seconds before the ink starts refilling
    public int damage;          // The damage each attack does
    public int radius;          // The radius of an attack
    public int groundRadius;    // The radius when using on the ground

    public Weapon(String name, String id, WeaponType type, int sps, float playerSpeed, float cooldown, int damage, int radius, int groundRadius) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.sps = sps;
        this.playerSpeed = playerSpeed;
        this.cooldown = cooldown;
        this.damage = damage;
        this.radius = radius;
        this.groundRadius = groundRadius;

        Bukkit.getLogger().info("Registered weapon " + name + " succesfully!");
    }
}
