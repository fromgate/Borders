package ru.nukkit.borders.commands;


import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.borders.util.Selector;
import ru.nukkit.borders.util.Message;


@CmdDefine(command = "border", alias = "brd", subCommands ={"(?i)p1|p2"} , permission = "border.config", description = Message.CMD_BRD_SEL)
public class CmdBrdSelect extends Cmd{


    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        if (args.length!=1) return false;
        if (args[0].equalsIgnoreCase("p1")) {
            Selector.selectP1(player);
            return Message.P1_SELECTED.print(player,Selector.locToStr(player.getLocation()));
        } else {
            Selector.selectP2(player);
            return Message.P2_SELECTED.print(player,Selector.locToStr(player.getLocation()));
        }
    }
}
