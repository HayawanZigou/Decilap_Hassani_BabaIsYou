package fr.umlv.baba.main;

import fr.umlv.baba.*;
import fr.umlv.baba.enumerate.EnumProperties;
import fr.umlv.zen5.Application;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.Event.Action;
import fr.umlv.zen5.KeyboardKey;
import fr.umlv.zen5.ScreenInfo;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * @author Decilap
 * @author Hassani
 */
public class Main {
	private static Level level;

	/**
	 * @param args contains the supplied command-line arguments.
	 */
	public static void main(String[] args) {
		String[] paths = { "default-level", "level1", "level2", "level3", "level4", "level5", "level6", "bonus-level" };

		if (args.length > 0) { // if a command is entered
			Level.checkCommands(args, paths);
		}
		for (var i = 0; i < paths.length; i++) // generate the paths to each level.
			paths[i] = "levels/" + paths[i] + ".txt";

		Application.run(Color.BLACK, context -> {
			ScreenInfo screenInfo = context.getScreenInfo();
			float width = screenInfo.getWidth();
			float height = screenInfo.getHeight();
			var level_number = 0;
			level = new Level(width, height, paths[level_number]);

			if (args.length >= 3 && args[0].equals("--level") && args[2].equals("--execute"))
				level.executeCommand(args);

			var you = (You) level.properties.get(EnumProperties.YOU);
			var push = (Push) level.properties.get(EnumProperties.PUSH);
			var stop = (Stop) level.properties.get(EnumProperties.STOP);
			var win = (Win) level.properties.get(EnumProperties.WIN);
			var melt = (Melt) level.properties.get(EnumProperties.MELT);
			var hot = (Hot) level.properties.get(EnumProperties.HOT);
			var breaker = (Breaker) level.properties.get(EnumProperties.BREAKER);
			var sink = (Sink) level.properties.get(EnumProperties.SINK);
			var defeat = (Defeat) level.properties.get(EnumProperties.DEFEAT);
			KeyboardKey key = null;

			while (true) {
				Event event = context.pollOrWaitEvent(10);
				context.renderFrame(graphics -> {
					graphics.fill(new Rectangle2D.Float(0, 0, width, height));
					level.drawLevel(graphics);
				});
				if (event == null) { // no event
					continue;
				}

				Action action = event.getAction();
				if (action == Action.KEY_PRESSED) {
					key = event.getKey();

					if (breaker.isBreaker(you)) {
						breaker.destroy(key, you.elements(), level.elements);
					}

					melt.removeMeltElement(level.elements, hot);
					sink.removeSinkElements(level.elements);
					defeat.removeDefeatElements(level.elements, you);

					if (args.length < 3)// if the command execute is not used
						level.generatePropertiesAndElements();

					if (you.isEmpty()) {
						System.out.println("lose");
						context.exit(0);
						return;
					}
					if (win.isWin(you) && level_number < paths.length - 1 && args.length > 1
							&& args[0].equals("--levels")) {
						level_number += 1;
						level = new Level(width, height, paths[level_number]);
						you = (You) level.properties.get(EnumProperties.YOU);
						push = (Push) level.properties.get(EnumProperties.PUSH);
						stop = (Stop) level.properties.get(EnumProperties.STOP);
						win = (Win) level.properties.get(EnumProperties.WIN);
						melt = (Melt) level.properties.get(EnumProperties.MELT);
						hot = (Hot) level.properties.get(EnumProperties.HOT);
						breaker = (Breaker) level.properties.get(EnumProperties.BREAKER);
						sink = (Sink) level.properties.get(EnumProperties.SINK);
						defeat = (Defeat) level.properties.get(EnumProperties.DEFEAT);
					}
					if (win.isWin(you) && (args.length <= 1 || level_number == paths.length - 1
							|| (args.length > 1 && args[0].equals("--level")))) {
						System.out.println("win");
						context.exit(0);
						return;
					}
					you.move(key, stop);
					push.pushable(key, you, stop);
				}

				if (key == KeyboardKey.Q) {
					context.exit(0);
					return;
				}
			}
		});
	}

}
