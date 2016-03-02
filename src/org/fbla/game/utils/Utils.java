package org.fbla.game.utils;

import java.awt.Font;
import java.awt.Shape;
import java.awt.geom.Area;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.fbla.game.Bridge;
import org.fbla.game.boards.GameBoard;
import org.fbla.game.sprites.Player;

import res.Audio;

public class Utils {

	private static List<Integer> ids = new ArrayList<>();
	private static File config;
	private static int player_level;
	public static int player_score;
	private static String root;
	private static File rootFile;
	public static boolean firsttime = false;
	public static HashMap<Integer, Background> backgrounds = new HashMap<>();
	public static HashMap<Integer, int[]> spawns = new HashMap<>();

	public static void init() {
		root = "C://KANSAS_WELLSVILLE_HIGHSCHOOL/master/";
		config = new File(root + "/config.txt");
		
		
		
		backgrounds.put(1, Background.SKY);
		backgrounds.put(2, Background.SKY);
		backgrounds.put(3, Background.SKY);
		backgrounds.put(4, Background.SKY);
		backgrounds.put(5, Background.SKY);
		backgrounds.put(6, Background.SKY);
		backgrounds.put(7, Background.SKY);
		backgrounds.put(8, Background.SKY);
		backgrounds.put(9, Background.ENTERING_CAVE);
		backgrounds.put(10, Background.CAVE);
		
		spawns.put(1, new int []{21,460});
		spawns.put(2, new int []{21,405});
		spawns.put(3, new int []{21,405});
		spawns.put(4, new int []{21,480});
		spawns.put(5, new int []{21,405});
		spawns.put(6, new int []{0,0});
		spawns.put(7, new int []{21,140});
		spawns.put(8, new int []{21,230});
		spawns.put(9, new int []{21,260});
		spawns.put(10, new int []{15,430});
		
		

		int length = (int) config.length();
		byte[] bytes = new byte[length];
		FileInputStream in;
		try {
			in = new FileInputStream(config);
			try {
				in.read(bytes);
			} catch (IOException e) {

				e.printStackTrace();
			} finally {
				try {
					in.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		}

		

		String contents = new String(bytes);
		String[] data = contents.split("-");
		for (String info : data) {
			if (info.contains("level"))
				player_level = Integer.parseInt(info.split(":")[1]);
			if (info.contains("score"))
				player_score = Integer.parseInt(info.split(":")[1]);
			
		}

		
	}

	public static void broadcastMessage(String message) {
		System.out.println(message);
		System.out.println();
	}
	
	public static int[] getSpawnPoint(int level){
		return spawns.get(level);
	}

	public static void displayMessage(int id, String message, int x, int y, int time, String color, int size,Font font) {
		((GameBoard)Bridge.getGame().getBoard()).setFont(font);
		((GameBoard)Bridge.getGame().getBoard()).getGraphics().setFont(font);
		((GameBoard)Bridge.getGame().getBoard()).messages.put(id,message + ":"+ ((x - ((GameBoard)Bridge.getGame().getBoard()).getFontMetrics(font).stringWidth(message)/ 2)) + ":" + y + ":" + time + ":" + color + ":" + size);
	}

	public static void addPlayerMessage(int id, String message, int xmod, int ymod, int time, String color, int size) {
		((GameBoard)Bridge.getGame().getBoard()).messages_player.put(id,
				message + ":" + xmod + ":" + ymod + ":" + time + ":" + color + ":" + size);
	}

	public static int getNextID() {
		try {
			int i = ids.size();
			ids.add(i);
			return i;
		} catch (IndexOutOfBoundsException ex) {
			ids.add(0);
			return 0;
		}
	}

	public static void savePlayerInfo(Player player) {
		config.delete();
		if (!config.getParentFile().mkdirs()) {
		}
		try {
			player_level = player.level;
			player_score = player.getScore();
		} catch (NullPointerException ex) {
		}

		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(config, true));
			out.append("level:" + player_level + "-");
			out.append("score:" + player_score);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		
		

	}

	public static void initPlayer(Player player) {
		player.level = player_level;
		player.setScore(player_score);

	}

	public static void runInstall() {
		
		broadcastMessage("install");
		
		firsttime = true;
		root = "C://KANSAS_WELLSVILLE_HIGHSCHOOL/master/";
		rootFile = new File(root);
		if (!rootFile.exists())
			rootFile.mkdirs();
		config = new File(root + "/config.txt");
		

		try {
			config.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (!config.getParentFile().mkdirs()) {
			
		}
		
		

		BufferedWriter out = null;

		try {
			out = new BufferedWriter(new FileWriter(config, true));
			out.append("level:" + 1 + "-");
			out.append("score:" + 0);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		

		init();

	}

	public static void checkPlayerInfo(Player player) {
		player.level = player_level;
	}



	

	public static void playSound(Sound sound) {
		Audio.playSound(sound);
		
	}
	
	public static boolean intersects(Shape shape1, Shape shape2){
		Area a1 = new Area(shape1);
		Area a2 = new Area(shape2);
		a1.intersect(a2);
		return !a1.isEmpty();
	}
	
	public static Background getBackground(int level){
		return backgrounds.get(level);
	}
	
	public static void setPlayerLevel(int level){
		player_level = level;
		try{
			if(Bridge.getGame().getBoard().getType().equals(BoardType.GAME_BOARD)){
				Bridge.getGame().loadLevel();
			}
		} catch(NullPointerException ex){}
		
	}

	public static int getPlayerLevel() {
		return player_level;
	}

}
