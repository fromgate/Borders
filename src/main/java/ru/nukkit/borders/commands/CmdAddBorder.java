package ru.nukkit.borders.commands;


import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import ru.nukkit.borders.Border;
import ru.nukkit.borders.Borders;
import ru.nukkit.borders.util.Selector;
import ru.nukkit.borders.util.Message;

@CmdDefine(command = "border", alias = "brd", subCommands ={"add","\\S+"} , permission = "border.config", description = Message.CMD_BRD_ADD)
public class CmdAddBorder extends Cmd {
    @Override
    public boolean execute(CommandSender sender, Player player, String[] args) {
        Border b = Selector.getBorder(player);
        if (b==null) return Message.ADD_FAIL_NEED_SEL.print(player);
        String name = args[1];
        if (Borders.exist(name)) return Message.ADD_FAIL_EXIST.print(sender,name);
        if (!name.matches("^[A-Za-z0-9_,'\\-\\+/]{1,}$")) return Message.INVALID_NAME.print(sender,"a-z, 0-9, -, _");
        Borders.add(name,b);
        return Message.ADD_OK.print(sender,name);
    }
}
