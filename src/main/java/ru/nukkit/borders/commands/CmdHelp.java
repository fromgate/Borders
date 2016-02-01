package ru.nukkit.borders.commands;


import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.borders.util.Message;

@CmdDefine(command = "border", alias = "brd", subCommands ={"(?i)help|hlp"} , permission = "border.config", description = Message.CMD_HLP)
public class CmdHelp extends Cmd{

    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        int page = args.length>1&&args[1].matches("\\d+") ? Integer.parseInt(args[1]) : 1;
        Commander.printHelp(sender,page);
        return true;
    }
}
