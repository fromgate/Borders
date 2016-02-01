package ru.nukkit.borders.commands;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.borders.util.Message;
import ru.nukkit.borders.util.Walker;

@CmdDefine(command = "border", alias = "brd", subCommands ={"(?i)walker|walk|wlk"} , permission = "border.walk", description = Message.CMD_BRD_WALK)
public class CmdWalker extends Cmd{
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        if (Walker.isWalker(player)) {
            Walker.removeWalker(player);
            Message.WALKER_OFF.print(player);
        } else {
            Walker.setWalker(player);
            Message.WALKER_ON.print(player);
        }
        return true;
    }
}
