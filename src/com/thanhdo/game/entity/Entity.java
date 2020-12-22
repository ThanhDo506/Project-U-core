package com.thanhdo.game.entity;

import com.thanhdo.game.graphics.Animation;
import com.thanhdo.game.graphics.Sprite;
import com.thanhdo.game.util.AABB;
import com.thanhdo.game.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
	public final int UP = 3;
	public final int DOWN = 0;
	public final int RIGHT = 2;
	public final int LEFT = 1;
	protected int currentAnimation;

	protected Animation ani;
	protected Sprite sprite;
	protected Vector2f pos;
	protected int size;

	protected boolean up;
	protected boolean down;
	protected boolean left;
	protected boolean right;
	protected boolean attack;
	protected boolean beAttacked;

	protected float dx;
	protected float dy;

	// *** Status
	protected String name;
	protected int maxHP = 100;
	protected int maxMP = 0;
	protected int nHP = maxHP;
	protected int nMP = maxMP;
	protected int level = 0;
	protected float healthpercent = 1;
	protected int defense = 100;
	protected int damage = 25;

	// *** Attack
	protected boolean attacking = false;
	protected int attackSpeed = 1050; // in milliseconds
	protected int attackDuration = 650; // in milliseconds
	protected double attacktime;
	protected boolean canAttack = true;

	// *** Speed and animation speed
	protected float maxSpeed = 3f;
	protected float acc = 3f;
	protected float deacc = 1.5f;

	protected int invincible = 500; // 100 - 300 //
	protected double invincibletime;
	protected boolean isInvincible = false;
	protected boolean die = false;

	protected AABB bounds;
	protected AABB hitBounds;

	public Entity(Sprite sprite, Vector2f orgin, int size) {
		this.sprite = sprite;
		pos = orgin;
		this.size = size;

		bounds = new AABB(orgin, size, size);
		hitBounds = new AABB(orgin, size, size);

		hitBounds.setxOffset(size / 2);
		hitBounds.setyOffset(size / 2);


		ani = new Animation();
		setAnimation(DOWN, sprite.getSpriteArray(DOWN), 10);
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setMaxSpeed(float value) {
		maxSpeed = value;
	}

	public void setAcc(float value) {
		acc = value;
	}

	public void setDeacc(float value) {
		deacc = value;
	}

	public AABB getBounds() {
		return bounds;
	}

	public int getSize() {
		return size;
	}

	public Animation getAnimation() {
		return ani;
	}

	public Vector2f getPos() {
		return pos;
	}

	public float getAcc() {
		return acc;
	}

	public float getDeacc() {
		return deacc;
	}

	public float getMaxSpeed() {
		return maxSpeed;
	}

	// * set Status

	public void setnHealth(int value){
		this.nHP -= value;
	}

	// * get Status

	public int getPercentHP() {
		return nHP * 100 / maxHP;
	}

	public int getAttackDuration() {
		return attackDuration;
	}

	public String getName() {
		return name;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public int getMaxMP() {
		return maxMP;
	}

	public int getnHP() {
		return nHP;
	}

	public int getnMP() {
		return nMP;
	}

	public int getLevel() {
		return level;
	}

	public boolean isDie(){
		return die;
	}

	// * Animation
	public void setAnimation(int direction, BufferedImage[] frames, int delay) {
		currentAnimation = direction;
		ani.setFrames(frames);
		ani.setDelay(delay);
	}

	public void animate() {
		if (up) {
			if (currentAnimation != UP || ani.getDelay() == -1) {
				setAnimation(UP, sprite.getSpriteArray(UP), 10);
			}
		} else if (down) {
			if (currentAnimation != DOWN || ani.getDelay() == -1) {
				setAnimation(DOWN, sprite.getSpriteArray(DOWN), 10);
			}
		} else if (left) {
			if (currentAnimation != LEFT || ani.getDelay() == -1) {
				setAnimation(LEFT, sprite.getSpriteArray(LEFT), 10);
			}
		} else if (right) {
			if (currentAnimation != RIGHT || ani.getDelay() == -1) {
				setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);
			}
		} else {
			setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
		}

	}

	private void setHitBoxDirection() {
		if (up && !attacking) {
			hitBounds.setxOffset((size - hitBounds.getWidth()) / 2);
			hitBounds.setyOffset(-hitBounds.getHeight() / 2 + hitBounds.getxOffset());
		} else if (down && !attacking) {
			hitBounds.setxOffset((size - hitBounds.getWidth()) / 2);
			hitBounds.setyOffset(hitBounds.getHeight() / 2 + hitBounds.getxOffset());
		} else if (left && !attacking) {
			hitBounds.setyOffset((size - hitBounds.getHeight()) / 2);
			hitBounds.setxOffset(-hitBounds.getWidth() / 2 + hitBounds.getyOffset());
		} else if (right && !attacking) {
			hitBounds.setyOffset((size - hitBounds.getHeight()) / 2);
			hitBounds.setxOffset(hitBounds.getWidth() / 2 + hitBounds.getyOffset());
		}
	}

	public void update() {

		/*
		 * if(isInvincible) { if((invincibletime / 1000000) + invincible < (time /
		 * 1000000) ) { isInvincible = false; } }
		 */
		animate();
		setHitBoxDirection();
		ani.update();
	}

	protected boolean isAttacking(double time) {

		if ((attacktime / 1000000) > ((time / 1000000) - attackSpeed)) {
			canAttack = false;
		} else {
			canAttack = true;
		}

		if ((attacktime / 1000000) + attackDuration > (time / 1000000)) {
			return true;
		}

		return false;
	}

	/*
	 * public void beAttacked(int value){ invincibletime = System.nanoTime();
	 * isInvincible = true; if(!isInvincible){ setHeath(value); }else {
	 * System.out.println("invincible"); invincibletime = System.nanoTime();
	 * isInvincible = true; } }
	 * 
	 * protected void setHeath(int value){ if(defense > value){ this.nHP -= 1; }else
	 * { this.nHP -= (value - defense); } isInvincible = true; }
	 */

	public abstract void render(Graphics2D g);
}
