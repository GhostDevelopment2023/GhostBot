package me.ghostdevelopment.ghostbot;

import me.ghostdevelopment.ghostbot.Commands.iCommand;
import me.ghostdevelopment.ghostbot.Utils.Color;
import me.ghostdevelopment.ghostbot.Utils.Logs;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.EventListener;

public class Main extends Logs {

	private static final ArrayList<EventListener> events = new ArrayList<>();

	private static String TOKEN;

	public static void main(String[] args) {

		if ( Float.parseFloat( System.getProperty( "java.class.version" ) ) < 61.0 )
		{
			log(Level.ERROR, Color.RED+"Java version must be 17 or higher.");
			return;
		}

		if(args.length!=2 || args[1].isBlank() || args[1].isEmpty()){
			log(Level.ERROR, "Can't start discord bot: "+ Color.RED +"Token missing.");
			return;
		}

		if(args[0].equalsIgnoreCase("--token") || args[0].equalsIgnoreCase("-t")){
			TOKEN=args[1];
		} else {
			log(Level.ERROR, "java -jar jar-name.jar --token <bot token>");
			return;
		}

		try {
			JDA jda = JDABuilder.createDefault(TOKEN)
					.enableIntents(GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS))
					.setStatus(OnlineStatus.DO_NOT_DISTURB)
					.addEventListeners(events)
					.build();
		}catch (Exception e){
			log(Level.ERROR, "Can't start discord bot: "+ Color.RED +"Token not valid.");
			return;
		}

		log(Level.LOG, Color.GREEN_BOLD+"Bot successfully started!");

	}

	private void registerCommands() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		String packageName = getClass().getPackage().getName();
		for(Class<? extends iCommand> clazz: new Reflections(packageName + ".commands.impl").getSubTypesOf(iCommand.class)){
			iCommand command = null;

			command = clazz.getDeclaredConstructor().newInstance();

			try {
				log(Level.LOG, "Loaded {0} event.");
			} catch (Exception e) {
				log(Level.ERROR, "Can't load {0} event");
			}
		}
	}

}