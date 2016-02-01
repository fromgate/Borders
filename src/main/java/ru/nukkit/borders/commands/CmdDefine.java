package ru.nukkit.borders.commands;


import ru.nukkit.borders.util.Message;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CmdDefine {
	String command();
	String alias() default "";
	String[] subCommands();
	String permission();
	boolean allowConsole() default false;
	Message description();

}

