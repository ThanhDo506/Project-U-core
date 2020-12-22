package com.thanhdo.game.states;

import com.thanhdo.game.GamePanel;
import com.thanhdo.game.entity.Animal;
import com.thanhdo.game.entity.Enemy;
import com.thanhdo.game.entity.NPC;
import com.thanhdo.game.entity.Player;

import com.thanhdo.game.graphics.Font;
import com.thanhdo.game.graphics.Sprite;
import com.thanhdo.game.tiles.TileManager;
import com.thanhdo.game.util.*;

import java.awt.*;
import java.util.ArrayList;

public class PlayState extends GameState {
	public static int score = 0;
	private Font font;

	private Player player;
	private Enemy monster1;

	private Animal ani1;
	// private NPC npc1;

	public static Vector2f map;

	private ArrayList<Enemy> enemies;
	private ArrayList<Animal> animals;
	private ArrayList<NPC> NPCs;

	private TileManager tm;
	private Camera cam;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		cam = new Camera(
				new AABB(new Vector2f(GamePanel.width / 2 - 1600 / 2, GamePanel.height / 2 - 900 / 2), 640, 480));
		tm = new TileManager("tile/final.tmx", cam);

		// sObj1 = new SkillObj1(new Sprite("NormalAttackAni/Effect 1 - Sprite
		// Sheet.png"), new Vector2f(GamePanel.width / 2 - 32 - 64, GamePanel.height / 2
		// - 64), 64, "Nana");
		// sObj = new SkillObj(new Sprite("NormalAttackAni/Effect 1 - Sprite
		// Sheet.png",32,32), new Vector2f(GamePanel.width / 2 - 64,GamePanel.height / 2
		// - 64), 64);
		enemies = new ArrayList<Enemy>();
		animals = new ArrayList<Animal>();
		NPCs = new ArrayList<NPC>();

		map = new Vector2f();
		Vector2f.setWorldVar(map.x, map.y);

		font = new Font("font/ShoFont.png", 16, (int) (256 / 6));

		player = new Player(new Sprite("characters/Female/Female 05-1.png"),
				new Vector2f(0 + GamePanel.width / 2 - 32, 0 + GamePanel.height / 2 - 32), 64);
		monster1 = new Enemy(new Sprite("characters/Enemy/Enemy 09-1.png"),
				new Vector2f(GamePanel.width / 2 - 32 + 500, GamePanel.height / 2 - 32 + 300), 64, "Nana");
		// monster2 = new Enemy(new Sprite("characters/Male/Male 02-1.png"), new
		// Vector2f(GamePanel.width / 2 - 32 + 150,GamePanel.height / 2 - 32 + 300), 64,
		// "ai", 100);
		ani1 = new Animal(new Sprite("characters/Animal/Cat 01-1r.png"), new Vector2f(600, 800), 64);
		// npc1 = new NPC("Rem", new Sprite("characters/Female/Female 15-1.png"), new
		// Vector2f(400, 1000), 64, true,"I love Sho 3000");

		// *** NPC

		NPCs.add(new NPC(" Rem", new Sprite("characters/Female/Female 15-1.png"), new Vector2f(3800, 660), 64, true,
				"Let's go"));
		NPCs.add(new NPC(" Ram", new Sprite("characters/Female/Female 16-1.png"), new Vector2f(2900, 430), 64, false,
				"Rem's waiting for u"));
		NPCs.add(new NPC(" Nino", new Sprite("characters/Female/Female 03-1.png"), new Vector2f(1025, 125), 64, false,
				"Help! Can you rescure the cat and dog"));
		NPCs.add(new NPC("Merlin", new Sprite("characters/Male/Male 04-1.png"), new Vector2f(3440, 1350), 64, false,
				"Sho is deverloper!"));
		NPCs.add(new NPC("Trooper", new Sprite("characters/Soldier/Soldier 04-1.png"), new Vector2f(4230, 1400), 64,
				false, "Go away kid!"));
		NPCs.add(new NPC("Trooper", new Sprite("characters/Soldier/Soldier 01-1.png"), new Vector2f(4230, 1650), 64,
				false, "Be careful!"));
		NPCs.add(new NPC(" Alid", new Sprite("characters/Male/Male 17-1.png"), new Vector2f(4680, 320), 64, false,
				"Zzz ... Zzz ..."));
		NPCs.add(new NPC("Nyanko", new Sprite("characters/Female/Female 12-1.png"), new Vector2f(1300, 230), 64, false,
				"Save the cats pls!"));
		NPCs.add(new NPC("", new Sprite("characters/Xmas/pipo-xmaschara05.png"), new Vector2f(2700, 245), 64, false,
				"Sho's snowman"));
		NPCs.add(new NPC("Troop", new Sprite("characters/pipo-charachip_soldier01.png"), new Vector2f(180, 1035), 64,
				false, "Where is my Light Saber ?"));
		NPCs.add(new NPC("Santa", new Sprite("characters/Xmas/pipo-xmaschara01.png"), new Vector2f(350, 3000), 64,
				false, "Merry X-Mas"));
		// *** Animals
		animals.add(new Animal(new Sprite("characters/Animal/Dog 01-1r.png"), new Vector2f(400, 1815), 64));
		animals.add(new Animal(new Sprite("characters/Animal/Dog-01-3r.png"), new Vector2f(200, 2155), 64));
		animals.add(new Animal(new Sprite("characters/Animal/Cat 01-1r.png"), new Vector2f(615, 3130), 64));
		animals.add(new Animal(new Sprite("characters/Animal/Cat 01-3.png"), new Vector2f(2830, 2155), 64));
		animals.add(new Animal(new Sprite("characters/Animal/Dog 01-1r.png"), new Vector2f(2360, 1850), 64));
		animals.add(new Animal(new Sprite("characters/Animal/Dog 01-3.png"), new Vector2f(5680, 65), 64));
		animals.add(new Animal(new Sprite("characters/Animal/Dog 01-1.png"), new Vector2f(5780, 1820), 64));
		animals.add(new Animal(new Sprite("characters/Animal/Dog 01-1r.png"), new Vector2f(3100, 2200), 64));
		animals.add(new Animal(new Sprite("characters/Animal/Dog 01-2.png"), new Vector2f(5440, 1790), 64));
		animals.add(new Animal(new Sprite("characters/Animal/Dog 01-1r.png"), new Vector2f(5900, 2965), 64));
		animals.add(new Animal(new Sprite("characters/Animal/Dog 01-1.png"), new Vector2f(2680, 2600), 64));
		animals.add(new Animal(new Sprite("characters/Animal/Dog 01-2.png"), new Vector2f(900, 3250), 64));
		animals.add(new Animal(new Sprite("characters/Animal/Dog 01-3.png"), new Vector2f(130, 4700), 64));
		animals.add(new Animal(new Sprite("characters/Animal/Dog 01-1r.png"), new Vector2f(350, 5780), 64));
		animals.add(new Animal(new Sprite("characters/Animal/Cat 01-3.png"), new Vector2f(2400, 5800), 64));
		animals.add(new Animal(new Sprite("characters/Animal/Dog 01-1r.png"), new Vector2f(4160, 6800), 64));
		animals.add(new Animal(new Sprite("characters/Animal/Dog 01-2.png"), new Vector2f(5900, 5900), 64));
		animals.add(new Animal(new Sprite("characters/Animal/Cat 01-3.png"), new Vector2f(5385, 3570), 64));

		// *** Monsters
	}

	public void update() {
		Vector2f.setWorldVar(map.x, map.y);
		monster1.update(player);
		// monster2.update(player,System.nanoTime());
		player.update(monster1);
		// sObj.update();
		// sObj1.update(player, System.currentTimeMillis());
		ani1.update(player);
		// npc1.update(player, System.currentTimeMillis());
		for (int i = 0; i < NPCs.size(); i++) {
			NPCs.get(i).update(player);
		}
		for (int i = 0; i < animals.size(); i++) {
			animals.get(i).update(player);
			if(animals.get(i).isDie()){
				animals.remove(i);
			}
		}
	}

	public void input(MouseHandler mouse, KeyHandler key) {
		player.input(mouse, key);
	}

	public void render(Graphics2D g) {
		tm.render(g);
		// TODO GUI

		// * Locate
		Sprite.drawArray1(g, font, "Locate:", new Vector2f(1375, 85), 12, 24, 12, 0);
		Sprite.drawArray1(g, font, "X: " + player.getPos().x + " Y: " + -player.getPos().y, new Vector2f(1375, 100), 12,
				24, 12, 0);
		// * Show FPS
		Sprite.drawArray(g, font, GamePanel.oldFrameCount + " FPS", new Vector2f(1400, 10), 32, 64, 30, 0);
		// * Show Status
		Sprite.drawArray(g, font, "Score: " + score, new Vector2f(GamePanel.width / 2, 10), 32, 64, 32, 0);
		/*
		 * Sprite.drawArray1(g, font, "HP: " + player.getnHP() + "/" +
		 * player.getMaxHP(),new Vector2f(30,10) , 16, 32, 16, 0); Sprite.drawArray1(g,
		 * font, "MP: " + player.getnMP() + "/" + player.getMaxMP(),new Vector2f(30,50)
		 * , 16, 32, 16, 0); Sprite.drawArray1(g, font, "LV: " + player.getLevel() ,new
		 * Vector2f(30,90) , 16, 32, 16, 0); Sprite.drawArray1(g, font, "EXP: " +
		 * player.getEXP() ,new Vector2f(30,110) , 16, 32, 16, 0); Sprite.drawArray1(g,
		 * font, "Stamina: " + player.getStamina() + "/" + player.getMaxStamina(), new
		 * Vector2f(30,130), 16, 32, 16, 0); Sprite.drawArray1(g, font, "Time: " +
		 * (long) (GamePanel.time/1000000000), new Vector2f(1375,57), 16, 32, 16, 0);
		 */

		Sprite.drawArray1(g, font, "HP: " + player.getPercentHP() + " %", new Vector2f(30, 10), 16, 32, 16, 0);
		Sprite.drawArray1(g, font, "Stamina: " + (int) player.getStamina() + "/" + (int) player.getMaxStamina(),
				new Vector2f(30, 50), 16, 32, 16, 0);
		// *** DONE GUI

		// * Render Objects
		player.render(g);
		monster1.render(g);
		ani1.render(g);
		// npc1.render(g);

		// sObj.render(g);
		// aaaaaaaaaaaaaaaaaaaaasObj1.render(g);
		// monster2.render(g);
		for (int i = 0; i < NPCs.size(); i++) {
			NPCs.get(i).render(g);
		}
		for (int i = 0; i < animals.size(); i++) {
			animals.get(i).render(g);
		}
	}
}
