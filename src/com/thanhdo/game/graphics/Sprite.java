package com.thanhdo.game.graphics;

import com.thanhdo.game.util.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Sprite
{
	private BufferedImage SPRITESHEET = null;
	private BufferedImage[][] spriteArray;
	// * tuy chon kich thuoc cua sprite
	private final int TILE_SIZE = 32;
	private int width;
	private int height;
	private int wSprite;
	private int hSprite;


	public Sprite(String file){
		width = TILE_SIZE;
		height = TILE_SIZE;

		System.out.println("Loading: " + file);
		SPRITESHEET = loadSprite(file);

		wSprite = SPRITESHEET.getWidth() / width;
		hSprite = SPRITESHEET.getHeight() / height;
		loadSpriteArray();
	}

	public Sprite(String file, int width, int height){
		this.width = width;
		this.height = height;

		System.out.println("Loading: " + file);
		SPRITESHEET = loadSprite(file);

		wSprite = SPRITESHEET.getWidth() / width;
		hSprite = SPRITESHEET.getHeight() / height;
		loadSpriteArray();
	}

	public Sprite(String file, int width, int height, int wSprite, int hSprite){
		this.width = width;
		this.height = height;

		System.out.println("Loading: " + file);
		SPRITESHEET = loadSprite(file);

		this.wSprite = SPRITESHEET.getWidth() / width;
		this.hSprite = SPRITESHEET.getHeight() / height;
		loadSpriteArray();
	}





	public void setSize(int width, int height){
		setWidth(width);
		setHeight(height);
	}

	public void setWidth(int i){
		width = i;
		wSprite = SPRITESHEET.getWidth() / width;
	}

	public void setHeight(int i){
		height = i;
		hSprite = SPRITESHEET.getHeight() / height;
	}

	public int getWidth(){
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	private BufferedImage loadSprite(String file){
		BufferedImage sprite = null;
		try{
			sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
		}catch (Exception e){
			System.out.println("ERROR: could not load file: " + file);
		}

		return sprite;
	}

	public void loadSpriteArray(){
		spriteArray = new BufferedImage[hSprite][wSprite];

		for (int j = 0; j < hSprite; j++){
			for (int i = 0; i < wSprite; i++){
				spriteArray[j][i] = getSprite(i, j);
			}
		}
	}


	public BufferedImage getSpriteSheet(){
		return SPRITESHEET;
	}

	public BufferedImage getSprite(int x, int y){
		return SPRITESHEET.getSubimage(x * width, y * height, width, height);
	}

	public BufferedImage[] getSpriteArray(int i){
		return spriteArray[i];
	}

	public BufferedImage[][] getSpriteArray2(int i){
		return spriteArray;
	}

	public static void drawArray(Graphics2D g, ArrayList<BufferedImage> img, Vector2f pos, int width, int height, int xOffset, int yOffset){
		float x = pos.x;
		float y = pos.y;

		for (int i = 0; i < img.size(); i++)
		{
			if(img.get(i) != null){
				g.drawImage(img.get(i), (int) x, (int) y, width, height ,null);
			}

			x += xOffset;
			y += yOffset;
		}
	}

	public static void drawArray(Graphics2D g, Font f, String word, Vector2f pos, int width, int height, int xOffset, int yOffset){
		float x = pos.x;
		float y = pos.y;
		int length = word.length()/4 * width;
		for (int i = 0; i < word.length(); i++)
		{
			if(word.charAt(i) != 32){
				g.drawImage(f.getFont(word.charAt(i)), (int) x - length, (int) y, width, height, null);
			}

			x += xOffset;
			y += yOffset;
		}
	}

	public static void drawArray1(Graphics2D g, Font f, String word, Vector2f pos, int width, int height, int xOffset, int yOffset){
		float x = pos.x;
		float y = pos.y;
		for (int i = 0; i < word.length(); i++)
		{
			if(word.charAt(i) != 32){
				g.drawImage(f.getFont(word.charAt(i)), (int) x, (int) y, width, height, null);
			}
			x += xOffset;
			y += yOffset;
		}
	}

	public int getSpriteSheetWidth() { return SPRITESHEET.getWidth(); }
	public int getSpriteSheetHeight() { return SPRITESHEET.getHeight(); }

}
