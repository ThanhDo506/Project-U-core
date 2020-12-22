package com.thanhdo.game.util;

// ? AABB Collisions: Xu? li' va cham. trong game

import com.thanhdo.game.entity.Entity;
import com.thanhdo.game.tiles.TileMap;
import com.thanhdo.game.tiles.TileMapObj;
import com.thanhdo.game.tiles.blocks.Block;

public class AABB
{
	private Vector2f pos;
	private float xOffset = 0;
	private float yOffset = 0;
	private float width;
	private float height;
	private float radius;
	private int size;
	private Entity e;

	public AABB(Vector2f pos, float width, float height){
		this.pos = pos;
		this.width = width;
		this.height = height;
		size = (int)Math.max(width, height);
	}

	public AABB(Vector2f pos, float radius){
		this.pos = pos;
		this.radius = radius;
		size = (int) radius;
	}

	public AABB(Vector2f pos, float radius ,Entity e){
		this.pos = pos;
		this.radius = radius;
		this.e = e;

		size = (int) radius;
	}

	public Vector2f getPos(){
		return pos;
	}

	public float getRadius(){
		return radius;
	}

	public float getWidth(){
		return width;
	}

	public float getHeight(){
		return height;
	}

	public void setBox(Vector2f pos, float width, float height){
		this.pos = pos;
		this.width = width;
		this.height = height;
		size = (int) Math.max(width, height);
	}

	public void setCircle(Vector2f pos, float radius){
		this.pos = pos;
		this.radius = radius;
		this.size = (int) radius;
	}


	public void setWidth(float width)
	{
		this.width = width;
	}

	public void setHeight(float height)
	{
		this.height = height;
	}

	public void setxOffset(float xOffset)
	{
		this.xOffset = xOffset;
	}

	public void setyOffset(float yOffset)
	{
		this.yOffset = yOffset;
	}

	public float getxOffset(){
		return xOffset;
	}

	public float getyOffset()
	{
		return yOffset;
	}

	public boolean collides(AABB bBox){
		float ax = (pos.getWorldVar().x + xOffset) + (this.width / 2);
		float ay = (pos.getWorldVar().y + yOffset) + (this.height / 2);
		float bx = (bBox.getPos().getWorldVar().x + (bBox.xOffset)) + (bBox.getWidth() / 2);
		float by = (bBox.getPos().getWorldVar().y + (bBox.yOffset)) + (bBox.getHeight() / 2);
		if((Math.abs(ax - bx) < (this.width / 2) + (bBox.width / 2)) && (Math.abs(ay - by) < ( bBox.height / 2) + (this.height/2) )){
			return true;
		}
		return false;
	}

	public boolean colCircleBox(AABB aBox){
		float dx = Math.max(aBox.getPos().getWorldVar().x + aBox.getxOffset(), Math.min(pos.getWorldVar().x + (radius / 2), aBox.getPos().getWorldVar().x + aBox.getxOffset() + aBox.getWidth()));
		float dy = Math.max(aBox.getPos().getWorldVar().y + aBox.getyOffset(), Math.min(pos.getWorldVar().y + (radius / 2), aBox.getPos().getWorldVar().y + aBox.getyOffset() + aBox.getHeight()));

		dx = pos.getWorldVar().x + (radius / 2) - dx;
		dy = pos.getWorldVar().y + (radius / 2) - dy;

		if(Math.sqrt(dx * dx + dy * dy) < (radius / 2)){
			return true;
		}

		return false;
	}

	public boolean collisionTile(float ax, float ay){
		for (int i = 0; i < 4; i++)
		{
			int xt = (int) ((pos.x + ax ) + (i % 2) * width + xOffset ) / 64;
			int yt = (int) ((pos.y + ay ) + (int)(i / 2) * height + yOffset ) / 64;

			if(TileMapObj.tmo_blocks.containsKey(String.valueOf(xt) + "," + String.valueOf(yt))){
				Block block = TileMapObj.tmo_blocks.get(String.valueOf(xt) + "," + String.valueOf(yt) );

				return TileMapObj.tmo_blocks.get(String.valueOf(xt) + "," + String.valueOf(yt)).update(this);
			}
		}

		return false;
	}

}
