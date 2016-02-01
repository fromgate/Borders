package ru.nukkit.borders.util;

import cn.nukkit.Player;
import cn.nukkit.level.Location;
import ru.nukkit.borders.Border;

import java.util.HashMap;
import java.util.Map;

public class Selector {

    private static Map<String,Location> p1 = new HashMap<String, Location>();
    private static Map<String,Location> p2 = new HashMap<String, Location>();


    public static void selectP1 (Player player){
        selectP1(player,player.getLocation());
    }

    public static void selectP2 (Player player){
        selectP2(player,player.getLocation());
    }

    public static void selectP1 (Player player, Location loc){
        p1.put(player.getName(),loc);
    }

    public static void selectP1 (Player player, int x, int z){
        Location loc = new Location(x,0,z,0,0,player.getLevel());
        selectP1(player,loc);
    }


    public static void selectP2 (Player player, Location loc){
        p2.put(player.getName(),loc);
    }

    public static void selectP2 (Player player, int x, int z){
        Location loc = new Location(x,0,z,0,0,player.getLevel());
        selectP2(player,loc);
    }

    public static Border getBorder (Player p){
        String name = p.getName();
        if (!p1.containsKey(name)) return null;
        if (!p2.containsKey(name)) return null;
        return new Border(p1.get(name),p2.get(name));
    }


    public static Location getP2 (Player player){
        if (player == null) return  null;
        if (!p2.containsKey(player.getName())) return null;
        return p2.get(player.getName());
    }

    public static Location getP1 (Player player){
        if (player == null) return  null;
        if (!p1.containsKey(player.getName())) return null;
        return p1.get(player.getName());
    }

    public static String locToStr(Location loc){
        return new StringBuilder(loc.getLevel().getName()).append("(").append(loc.getFloorX()).append(", ").append(loc.getFloorZ()).append(")").toString();

    }

    public static void clear(Player player) {
        String name= player.getName();
        if (p1.containsKey(name)) p1.remove(name);
        if (p2.containsKey(name)) p2.remove(name);
    }

}
