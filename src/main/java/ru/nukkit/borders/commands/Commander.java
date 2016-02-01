package ru.nukkit.borders.commands;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.PluginBase;
import ru.nukkit.borders.util.Message;
import ru.nukkit.borders.util.Paginator;

import java.util.ArrayList;
import java.util.List;

public class Commander {
	private static List<Cmd> commands = new ArrayList<Cmd>();
	private static PluginBase plugin;

    public static void init (PluginBase plg){
		plugin = plg;
        addNewCommand(new CmdBrdSelect(),Message.CMD_BRD);
        addNewCommand(new CmdBrdSelectCoord());
        addNewCommand(new CmdAddBorder());
        addNewCommand(new CmdList());
        addNewCommand(new CmdReload());
        addNewCommand(new CmdRemove());
        addNewCommand(new CmdWalker());
        addNewCommand(new CmdCheck());
        addNewCommand(new CmdHelp());
	}

	public static PluginBase getPlugin(){
		return plugin;
	}


    private static boolean isRegistered(String cmdStr){
        for (Cmd cmd : commands)
            if (cmd.getCommand().equalsIgnoreCase(cmdStr)) return true;
        return false;
    }

    public static boolean addNewCommand (Cmd cmd){
        return addNewCommand(cmd,null);
    }

	public static boolean addNewCommand (Cmd cmd, Message desc){
		if (cmd.getCommand() == null) return false;
		if (cmd.getCommand().isEmpty()) return false;
        if (!isRegistered(cmd.getCommand()))        {
            CommandExecutor newCmd = new CommandExecutor(cmd.getCommand());
            newCmd.setDescription(desc == null ?cmd.getDescription() : desc.toString());
            newCmd.setAliases(cmd.getAliases());
            plugin.getServer().getCommandMap().register(plugin.getName()+"_cmd", newCmd);
            Message.COMMAND_REGISTERED.log("NOCOLOR",cmd.toString());
        }
		commands.add(cmd);
		return true;
	}

	public static boolean isPluginYml(String cmdStr){
		return plugin.getDescription().getCommands().containsKey(cmdStr);
	}

	public static void printHelp(CommandSender sender, int page) {
        List<String> helpList = new ArrayList<String>();
        for (Cmd cmd : commands) {
            helpList.add(cmd.getDescription());
        }
        int pageHeight = (sender instanceof Player) ? 9 : 1000;
        Paginator.printPage(sender, helpList, Message.HLP_TITLE.getText('6'), Message.FOOTER.getText('e'), "No help", page, pageHeight, false);
    }

	public static String unsplit(String [] args){
		return unsplit (args,0);
	}
	
	public static String unsplit(String [] args, int num){
		if (args.length<=num) return "";
		StringBuilder sb = new StringBuilder();
		for (int i = num; i<args.length; i++){
			if (sb.length()>0)sb.append(" ");
			sb.append(args[i]);
		}
		return sb.toString();
	}
	
	public static Player getPlayer (CommandSender sender){
		return sender instanceof Player ? (Player) sender : null;
	}

    public static boolean execute(CommandSender sender, String cmdLabel, String[] args) {
        for (Cmd cmd : commands){
            if (!cmd.isCommand(cmdLabel)) continue;
            if (cmd.executeCommand(sender, args)) return true;
        }
        return false;
    }

	public static String getCommandByAlias (String cmdLabel){
        for (Cmd cmd : commands)
            if (cmd.isCommand(cmdLabel)) return cmd.getCommand();
        return null;
	}
    public static boolean isPluginCommand (String cmdLabel){
        return getCommandByAlias(cmdLabel)!=null;
    }


}
