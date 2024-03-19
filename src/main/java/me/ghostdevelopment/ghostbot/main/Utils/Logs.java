package me.ghostdevelopment.ghostbot.main.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings({"CallToPrintStackTrace", "unused"})
public abstract class Logs {
	private static final String LOG_FILE_EXTENSION = ".log";
	private static final String DATE_FORMAT = "dd_MM_yyyy-HH_mm_ss";
	private static final String dir = "./logs/";

	private static PrintWriter writer;

	static {
		try {
			String timeStamp = new SimpleDateFormat(DATE_FORMAT).format(new Date());

			File folder = new File(dir);

			if (!folder.exists()) {
				folder.mkdirs();
			}

			String fileName = timeStamp + LOG_FILE_EXTENSION;
			writer = new PrintWriter(new FileWriter(dir+fileName), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public enum Level {

		LOG(Color.CYAN+"[LOG] "+Color.RESET),
		WARNING(Color.YELLOW+"[WARNING] "+Color.RESET),
		ERROR(Color.RED+"[ERROR] "+Color.RESET),

		;
		final String level;
		Level(String level){
			this.level=level;
		}

	}

	public static void log(Level level, String message) {
		String formattedTimestamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
		String logEntry = "[" + formattedTimestamp + "] " + level.level + message;

		writer.println(Color.removeColors(logEntry));
		System.out.println(logEntry);
	}
}
