package me.ghostdevelopment.ghostbot.main;

import lombok.SneakyThrows;
import me.ghostdevelopment.ghostbot.main.Commands.iCommand;
import me.ghostdevelopment.ghostbot.main.Utils.Color;
import me.ghostdevelopment.ghostbot.main.Utils.Logs;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.EventListener;

public class Bot extends Logs {

	private static final ArrayList<EventListener> events = new ArrayList<>();

	@SneakyThrows
	public static void init(String TOKEN) {

		JDA jda = JDABuilder.createDefault(TOKEN)
				.enableIntents(GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS))
				.setStatus(OnlineStatus.DO_NOT_DISTURB)
				.addEventListeners(events)
				.setActivity(Activity.streaming("GhostBot By GhostAndry", "https://github.com/GhostAndry"))
				.build();

		registerCommands(jda);

		log(Level.LOG, Color.GREEN_BOLD+"Bot successfully started!");

	}

	private static void registerCommands(JDA jda) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		String packageName = Bot.class.getPackage().getName();
		for(Class<? extends iCommand> clazz: new Reflections(packageName + ".Commands.impl").getSubTypesOf(iCommand.class)){
			iCommand command = null;

			command = clazz.getDeclaredConstructor().newInstance();


			try {

				jda.updateCommands(
						Commands.slash(command.)
				).queue();

				log(Level.LOG, "Loaded {0} event.");
			} catch (Exception e) {
				log(Level.ERROR, "Can't load {0} event");
			}
		}
	}

}