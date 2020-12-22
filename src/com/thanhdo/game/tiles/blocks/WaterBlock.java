package com.thanhdo.game.tiles.blocks;

import com.thanhdo.game.util.AABB;
import com.thanhdo.game.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WaterBlock extends Block
{

	public WaterBlock(BufferedImage img, Vector2f pos, int width, int height)
	{
		super(img, pos, width, height);
	}

	@Override
	public boolean update(AABB p)
	{
		return false;
	}

	public void render(Graphics2D g){
		g.setColor(Color.green);
		g.drawImage(img, (int) pos.getWorldVar().x, (int) pos.getWorldVar().y, width, height, null);
	}

}
