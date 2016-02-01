package ru.nukkit.borders.commands;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.borders.Borders;
import ru.nukkit.borders.util.Message;


@CmdDefine(command = "border", alias = "brd", subCommands ={"(?i)remove|rmv|delete|dlt","\\S+"}, permission = "border.config", description = Message.CMD_BRD_REMOVE, allowConsole = true)
public class CmdRemove extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        String id = args[1];
        if (!Borders.exist(id)) Message.BRD_UNKNOWN.print(sender,id);
        Borders.remove (id);
        return Message.BRD_REMOVED.print(sender);
    }
}
