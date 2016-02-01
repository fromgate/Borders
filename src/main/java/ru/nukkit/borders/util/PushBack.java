package ru.nukkit.borders.util;

import cn.nukkit.Player;
import cn.nukkit.level.Location;

import java.util.HashMap;
import java.util.Map;

public class PushBack {
    private static Map<String,Location> pushback = new HashMap<String, Location>();

    public static void updateLoc (Player player, Location loc){
        pushback.put(player.getName(),loc);
    }

    public static Location getLocation (Player player){
        if (pushback.containsKey(player.getName())) return pushback.get(player.getName());
        return null;
    }
    public static void clearLoc (Player player){
        if (pushback.containsKey(player.getName())) pushback.remove(player.getName());
    }



}
