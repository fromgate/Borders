package ru.nukkit.borders.util;

import cn.nukkit.Player;

import java.util.HashSet;
import java.util.Set;

public class Walker {
    private static Set<String> walkers = new HashSet<String>();

    public static void setWalker (Player player){
        walkers.add(player.getName());
    }

    public static void removeWalker (Player player){
        if (walkers.contains(player.getName())) walkers.remove(player.getName());
    }

    public static boolean isWalker (Player player){
        return walkers.contains(player.getName());
    }


}
