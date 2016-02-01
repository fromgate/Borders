package ru.nukkit.borders.commands;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.borders.Borders;
import ru.nukkit.borders.util.Message;

@CmdDefine(command = "border", alias = "brd", subCommands ={"(?i)reload"} , permission = "border.config", description = Message.CMD_BRD_RELOAD, allowConsole =  true)
public class CmdReload extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        Borders.load();
        return Message.BRD_RELOADED.print(sender,Borders.getBorderList().size());
    }
}
