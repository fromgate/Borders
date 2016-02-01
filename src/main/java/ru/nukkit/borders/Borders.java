package ru.nukkit.borders;

import cn.nukkit.Player;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

import java.io.File;
import java.util.*;

public class Borders {

    private static Map<String,Border> borders = new TreeMap<String, Border>(String.CASE_INSENSITIVE_ORDER);

    public static void init(){
        load();
    }

    public static void load(){
        BordersPlugin.getPlugin().getDataFolder().mkdirs();
        File f = new File(BordersPlugin.getPlugin().getDataFolder()+File.separator+"borders.yml");
        if (!f.exists()) try {
            f.createNewFile();
        } catch (Exception e) {
        }
        Config cfg = new Config(f,Config.YAML);
        for (String key : cfg.getAll().keySet()){
            if (key.contains(".")) continue;
            String value = cfg.getString(key,"");
            if (value == null||value.isEmpty()||!value.matches("\\S+,\\s*\\d+,\\s*\\d+,\\s*\\d+,\\s*\\d+")) continue;
            String[] ln = value.split(",\\s*");
            Border border = new Border(ln[0],Integer.parseInt(ln[1]),Integer.parseInt(ln[2]),Integer.parseInt(ln[3]),Integer.parseInt(ln[4]));
            borders.put(key,border);
        }
    }

    public static void save(){
        File f = new File(BordersPlugin.getPlugin().getDataFolder()+File.separator+"borders.yml");
        if (f.exists()) f.delete();
        try {
            f.createNewFile();
        } catch (Exception e) {
        }
        Config cfg = new Config(f,Config.YAML);
        for (String key : borders.keySet()){
            Border b = borders.get(key);
            cfg.set(key, b.toString());
        }
        cfg.save();
    }

    public static void add (String id, Border border){
        borders.put(id,border);
        save();
    }

    public static void add (String id, Location loc1, Location loc2){
        add (id,new Border(loc1,loc2));
    }

    public static List<Border> getBorderInWorld(Level level){
        List<Border> brd = new ArrayList<Border>();
        for (Border b : borders.values())
            if (b.world.equalsIgnoreCase(level.getName())) brd.add(b);
        return brd;
    }

    public static boolean allowedLocation(Player player, Location location) {
        List<Border> brd = getBorderInWorld(player.getLevel());
        if (brd.isEmpty()) return true;
        for (Border b : brd)
            if (b.isLocInBorder(location)) return true;
        return false;
    }

    public static List<String> getBorderList() {
        return getBorderList(borders);
    }

    public static List<String> getBorderList(Map<String,Border> borders) {
        List<String> lst = new ArrayList<String>();
        for (String key : borders.keySet())
            lst.add(TextFormat.GREEN+ key+" : "+TextFormat.DARK_GREEN+borders.get(key).toPrintString());
        return lst;
    }

    public static boolean exist(String id) {
        return borders.containsKey(id);
    }

    public static void remove(String id) {
        if (borders.containsKey(id)) borders.containsKey(id);
    }

    public static Map<String, Border> getBordersInLoc(Location location) {
        Map<String,Border> brd = new HashMap<String, Border>();
        for (String key : borders.keySet()){
            Border b = borders.get(key);
            if (b.isLocInBorder(location)) brd.put(key,b);
        }
        return brd;
    }
}
