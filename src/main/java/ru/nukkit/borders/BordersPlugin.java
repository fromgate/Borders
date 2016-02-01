package ru.nukkit.borders;

import cn.nukkit.plugin.PluginBase;
import ru.nukkit.borders.commands.Commander;
import ru.nukkit.borders.util.BorderListener;
import ru.nukkit.borders.util.Message;

public class BordersPlugin extends PluginBase{

    private static BordersPlugin instance;

    @Override
    public void onEnable(){
        instance = this;
        getServer().getPluginManager().registerEvents(new BorderListener(),this);
        Message.init(this);
        Commander.init(this);
        Borders.init();
    }

    public static BordersPlugin getPlugin() {
        return instance;
    }
}
