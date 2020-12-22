package com.thanhdo.game.tiles.blocks;

import com.thanhdo.game.util.AABB;
import com.thanhdo.game.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ObjBlock extends Block
{

	public ObjBlock(BufferedImage img, Vector2f pos, int width, int height)
	{
		super(img, pos, width, height);
	}

	@Override
	public boolean update(AABB p)
	{
		return true;
	}

	public void render(Graphics2D g){
		g.setColor(Color.white);
		g.drawImage(img, (int) pos.getWorldVar().x, (int) pos.getWorldVar().y, width, height, null);
	}

}
