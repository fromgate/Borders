package ru.nukkit.borders.commands;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.borders.util.Selector;
import ru.nukkit.borders.util.Message;


@CmdDefine(command = "border", alias = "brd", subCommands ={"(?i)p1|p2","\\d+","\\d+"} , permission = "border.config", description = Message.CMD_BRD_SEL2)

public class CmdBrdSelectCoord extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        if (args.length!=3) return false;
        int x = Integer.parseInt(args[1]);
        int z = Integer.parseInt(args[2]);
        if (args[0].equalsIgnoreCase("p1")) {
            Selector.selectP1(player,x,z);
            return Message.P1_SELECTED.print(player,Selector.locToStr(Selector.getP1(player)));
        } else {
            Selector.selectP2(player,x,z);
            return Message.P2_SELECTED.print(player,Selector.locToStr(Selector.getP2(player)));
        }
    }
}
