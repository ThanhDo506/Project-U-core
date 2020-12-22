package com.thanhdo.game.tiles;

import com.thanhdo.game.graphics.Sprite;
import com.thanhdo.game.tiles.blocks.*;
import com.thanhdo.game.util.Vector2f;

import java.awt.*;
import java.util.HashMap;

public class TileMapObj extends TileMap
{

	public static HashMap<String, Block> tmo_blocks;

	public TileMapObj(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns){
		Block tempBlock;

		tmo_blocks = new HashMap<String, Block>();

		String[] block = data.split(",");
		for (int i = 0; i < (width * height); i++)
		{
			int temp = Integer.parseInt(block[i].replaceAll("\\s+", ""));
			if(temp != 0){
					tempBlock = new ObjBlock(sprite.getSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns)),new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight),tileWidth, tileHeight);
					tmo_blocks.put(String.valueOf((int) (i % width)) + "," + String.valueOf((int) i / height), tempBlock);

			/*else if (isObjectBLock(temp)){
					tempBlock = new ObjBlock(sprite.getSprite((int) ((temp - 1) % tileColumns), (int) ((temp - 1) / tileColumns) ), new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight), tileWidth, tileHeight);
				}*/
				//tmo_blocks.put(String.valueOf((int) (i % width)) + "," + String.valueOf((int) i / height), tempBlock);
			}
		}
	}

	private boolean isObjectBLock(int block){
		int arr[] = {4,24,25,26,44,46,64,65,66,69,70,71,103,104,105,109,110,111,112,113,123,124,125,126,127,128,131,153};
		for (int i: arr)
		{
			if (i == block){
				return true;
			}
		}
		return false;
	}

	public void render(Graphics2D g){
		for (Block i: tmo_blocks.values())
		{
			i.render(g);
		}
	}
}
