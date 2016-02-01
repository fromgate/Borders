package ru.nukkit.borders.util;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.block.BlockPlaceEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerMoveEvent;
import cn.nukkit.level.Location;
import ru.nukkit.borders.Borders;

public class BorderListener implements Listener{


    @EventHandler (ignoreCancelled = true, priority = EventPriority.NORMAL)
    public void onPlayerMove(PlayerMoveEvent event){
        if (Walker.isWalker(event.getPlayer())) return;
        if (event.getFrom().getFloorX()==event.getTo().getFloorX()&&
                event.getFrom().getFloorY()==event.getTo().getFloorY()&&
                event.getFrom().getFloorZ()==event.getTo().getFloorZ()) return;
        if (Borders.allowedLocation (event.getPlayer(), event.getTo())) return;
        Location back = PushBack.getLocation(event.getPlayer());
        if (back == null) back = event.getFrom();
        Message.BRD_FORBID_MOVE.print(event.getPlayer(),'c');
        event.setCancelled();
        if (!Borders.allowedLocation(event.getPlayer(),back)) {
            Message.BRD_TP_SPAWN.print(event.getPlayer(),'c');
            back = Location.fromObject(event.getPlayer().getLevel().getSpawnLocation(),event.getPlayer().getLevel());
        }
        teleportPlayer (event.getPlayer(),back);
    }

    @EventHandler (ignoreCancelled = true, priority = EventPriority.NORMAL)
    public void onBreak (BlockBreakEvent event){
        if (Walker.isWalker(event.getPlayer())) return;
        Location blockLoc = new Location(event.getBlock().getX(),
                event.getBlock().getY(),event.getBlock().getZ(),0,0,event.getBlock().getLevel());
        if (Borders.allowedLocation (event.getPlayer(), blockLoc)) return;
        Message.BRD_FORBID_BREAK.print(event.getPlayer(),'c');
        event.setCancelled();
    }

    @EventHandler (ignoreCancelled = true, priority = EventPriority.NORMAL)
    public void onPlace (BlockPlaceEvent event){
        if (Walker.isWalker(event.getPlayer())) return;
        Location blockLoc = new Location(event.getBlock().getX(),
                event.getBlock().getY(),event.getBlock().getZ(),0,0,event.getBlock().getLevel());
        if (Borders.allowedLocation (event.getPlayer(), blockLoc)) return;
        Message.BRD_FORBID_BUILD.print(event.getPlayer(),'c');
        event.setCancelled();

    }

    @EventHandler (ignoreCancelled = true, priority = EventPriority.NORMAL)
    public void playerJoin (PlayerJoinEvent event){
        PushBack.clearLoc(event.getPlayer());
        Selector.clear(event.getPlayer());

    }

    private void teleportPlayer (final Player player, final Location loc){
        Server.getInstance().getScheduler().scheduleDelayedTask(new Runnable() {
            public void run() {
                if (!player.isOnline()) return;
                player.teleport(loc);
            }
        },1);
    }

}
