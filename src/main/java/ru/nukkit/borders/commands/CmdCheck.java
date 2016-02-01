package ru.nukkit.borders.commands;


import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.borders.Border;
import ru.nukkit.borders.Borders;
import ru.nukkit.borders.util.Message;
import ru.nukkit.borders.util.Paginator;

import java.util.List;
import java.util.Map;

@CmdDefine(command = "border", alias = "brd", subCommands ={"(?i)check|info|chk"} , permission = "border.check", description = Message.CMD_BRD_CHECK)
public class CmdCheck extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        Map<String, Border> brd = Borders.getBordersInLoc(player.getLocation());
        if (brd.isEmpty()) return Message.CHK_NOT_FOUND.print(sender);
        List<String> lst = Borders.getBorderList(brd);
        Paginator.printPage(sender,lst,Message.CHK_FOUND.getText('6'),Message.FOOTER.getText('e'),"",1,0,true);
        return true;
    }
}
