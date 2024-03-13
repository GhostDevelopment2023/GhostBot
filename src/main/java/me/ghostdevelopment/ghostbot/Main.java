package me.ghostdevelopment.ghostbot;

import me.ghostdevelopment.ghostbot.Events.GhostEvent;
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
	private static final String HELP_MSG = "Args: \n" +
			"    --token|-t [discord bot token]";

	public static void main(String[] args) {

		if(args.length==0){
			System.out.println(HELP_MSG);
			return;
		}
		if ( args.length==2 && (args[0].equalsIgnoreCase("--token")||args[0].equalsIgnoreCase("-t"))) TOKEN = args[1];
		else{
			System.out.println(HELP_MSG);
			return;
		}

		JDA jda = JDABuilder.createDefault(TOKEN)
				.enableIntents(GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS))
				.setStatus(OnlineStatus.DO_NOT_DISTURB)
				.addEventListeners(events)
				.build();

		log(Level.LOG, Color.GREEN_BOLD+"Bot successfully started!");

	}

	private void registerCommands() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		String packageName = getClass().getPackage().getName();
		for(Class<? extends GhostEvent> clazz: new Reflections(packageName + ".commands.commands").getSubTypesOf(GhostEvent.class)){
			GhostEvent command = null;

			command = clazz.getDeclaredConstructor().newInstance();

			try {

				log(Level.LOG, "Loaded $command event.");
			} catch (Exception e) {
				log(Level.ERROR, "Can't load $command event");
			}
		}
	}

}