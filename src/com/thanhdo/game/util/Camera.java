package com.thanhdo.game.util;

import com.thanhdo.game.entity.Entity;
import com.thanhdo.game.states.PlayState;

import java.awt.*;

public class Camera
{
	private AABB collisionCam;
	private AABB bounds;

	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;

	private float dx;
	private float dy;
	private float maxSpeed = 4f;
	private float acc = 2f;
	private float deacc = 1.5f;

	private int widthLimit;
	private int heightLimit;

	private Entity e;

	public Camera(AABB collisionCam){
		this.collisionCam = collisionCam;
	}

	public AABB getBounds(){
		return collisionCam;
	}



	public void setLimit(int widthLimit, int heightLimit){
		this.widthLimit =  widthLimit;
		this.heightLimit =  heightLimit;
	}

	public void update(){
		PlayState.map.x += dx;
		PlayState.map.y += dy;
		move();
	}

	private void move(){
		if(up){
			dy -= acc;
			if(dy < -maxSpeed){
				dy = -maxSpeed;
			}
		}else {
			if (dy < 0){
				dy += deacc;
				if(dy > 0){
					dy = 0;
				}
			}
		}

		if(down){
			dy += acc;
			if(dy > maxSpeed){
				dy = maxSpeed;
			}
		}else {
			if (dy > 0){
				dy -= deacc;
				if(dy < 0){
					dy = 0;
				}
			}
		}

		if(left){
			dx -= acc;
			if(dx < -maxSpeed){
				dx = -maxSpeed;
			}
		}else {
			if (dx < 0){
				dx += deacc;
				if(dx > 0){
					dx = 0;
				}
			}
		}

		if(right){
			dx += acc;
			if(dx > maxSpeed){
				dx = maxSpeed;
			}
		}else {
			if (dx > 0){
				dx -= deacc;
				if(dx < 0){
					dx = 0;
				}
			}
		}
	}

	public void target(Entity e){
		this.e = e;
		deacc = e.getDeacc();
		acc = e.getAcc();
		maxSpeed = e.getMaxSpeed();
	}

	public void input(MouseHandler mouse, KeyHandler key){
		if(e == null){
			if(key.up.down){
				up = true;
			} else {
				up = false;
			}
			if(key.down.down){
				down = true;
			} else {
				down = false;
			}
			if(key.left.down){
				left = true;
			} else {
				left = false;
			}
			if(key.right.down){
				right = true;
			} else {
				right = false;
			}
		}
	}

	public void render(Graphics2D g){

	}
}
