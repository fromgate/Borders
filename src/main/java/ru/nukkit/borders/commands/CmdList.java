package ru.nukkit.borders.commands;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.borders.Borders;
import ru.nukkit.borders.util.Message;
import ru.nukkit.borders.util.Paginator;

import java.util.List;

@CmdDefine(command = "border", alias = "brd", subCommands ={"(?i)list|lst"}, permission = "border.config", description = Message.CMD_BRD_LIST, allowConsole = true)
public class CmdList extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        int page = args.length>1&&args[1].matches("\\d+") ? Integer.parseInt(args[1]) : 1;
        List<String> lst = Borders.getBorderList();
        Paginator.printPage(sender,lst,Message.BRD_LST_TITLE.getText('6'),Message.FOOTER.getText('e'),Message.BRD_LST_EMPTY.getText('6'),page,0,true);
        return true;
    }
}
