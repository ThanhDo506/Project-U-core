package com.thanhdo.game.tiles;

import com.thanhdo.game.graphics.Sprite;
import com.thanhdo.game.tiles.blocks.Block;
import com.thanhdo.game.tiles.blocks.NormBlock;
import com.thanhdo.game.util.Vector2f;

import java.awt.*;
import java.util.ArrayList;

public class TileMapNorm extends TileMap
{
	private ArrayList<Block> blocks;
	public TileMapNorm(String data , Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns){
		blocks = new ArrayList<Block>();

		String[] block = data.split(",");
		for(int i = 0; i < (width * height); i++){
			int temp = Integer.parseInt(block[i].replaceAll("\\s+",""));
			if(temp != 0){
				blocks.add(new NormBlock(sprite.getSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns)),new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight),tileWidth, tileHeight));
			}
		}
	}

	private boolean isNormalBLock(int block){
		int arr[] = {5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,27,28,29,30,31,32,33,34,35,36,37,38,39,45,47,48,49,50,51,52,53,54,55,56,57,58,59,67,68,72,73,74,75,76,77,78,80,81,82,83,84,85,86,87,88,89,90,92,94,95,96,97,100,101,102,106,107,108,120,121,122,129,130,132,133,149,150,151,152};
		for (int i: arr)
		{
			if (i == block){
				return true;
			}
		}
		return false;
	}

	public void render(Graphics2D g){
		for (int i = 0; i < blocks.size(); i++)
		{
			blocks.get(i).render(g);
		}
	}
}
