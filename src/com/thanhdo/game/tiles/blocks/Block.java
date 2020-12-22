package com.thanhdo.game.tiles.blocks;

import com.thanhdo.game.util.AABB;
import com.thanhdo.game.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Block
{
	protected int width;
	protected int height;

	protected BufferedImage img;
	protected Vector2f pos;

	public Block(BufferedImage img, Vector2f pos, int width, int height){
		this.img = img;
		this.pos = pos;
		this.width = width;
		this.height = height;
	}

	public abstract boolean update(AABB p);

	public void render(Graphics2D g){
		g.drawImage(img, (int) pos.getWorldVar().x, (int) pos.getWorldVar().y, width, height, null);
	}
}
