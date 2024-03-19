package me.ghostdevelopment.ghostbot;

import me.ghostdevelopment.ghostbot.main.Bot;
import me.ghostdevelopment.ghostbot.main.Utils.Color;
import me.ghostdevelopment.ghostbot.main.Utils.Logs;

public class BootsTrap extends Logs {

    private static String TOKEN;

    public static void main(String[] args) {
        if ( Float.parseFloat( System.getProperty( "java.class.version" ) ) < 61.0 )
        {
            log(Logs.Level.ERROR, Color.RED+"Java version must be 17 or higher.");
            return;
        }

        if(args.length!=2 || args[1].isBlank() || args[1].isEmpty()){
            log(Logs.Level.ERROR, "Can't start discord bot: "+ Color.RED +"Token missing.");
            return;
        }

        if(args[0].equalsIgnoreCase("--token") || args[0].equalsIgnoreCase("-t")){
            TOKEN=args[1];
        } else {
            log(Logs.Level.ERROR, "java -jar jar-name.jar --token <bot token>");
            return;
        }

        Bot.init(TOKEN);
    }
}
