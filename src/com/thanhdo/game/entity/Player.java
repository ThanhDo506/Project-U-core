package com.thanhdo.game.entity;

import com.thanhdo.game.graphics.Sprite;
import com.thanhdo.game.states.PlayState;
import com.thanhdo.game.util.KeyHandler;
import com.thanhdo.game.util.MouseHandler;
import com.thanhdo.game.util.Vector2f;

import java.awt.*;

public class Player extends Entity {
	private Sprite attackAnimation;

	public int EXP = 0;
	private float maxStamina = 100;
	private float nStamina = maxStamina;

	private boolean dash;

	public Player(Sprite sprite, Vector2f orgin, int size) {
		super(sprite, orgin, size);
		acc = 2f;
		maxSpeed = 3f;
		bounds.setWidth(42);
		bounds.setHeight(20);
		bounds.setxOffset(12);
		bounds.setyOffset(40);
		maxHP = 3000;
		nHP = maxHP;
		maxMP = 100;
		nMP = maxMP;
	}

	public void move() {
		if (up) {
			dy -= acc;
			if (dy < -maxSpeed) {
				dy = -maxSpeed;
			}
		} else {
			if (dy < 0) {
				dy += deacc;
				if (dy > 0) {
					dy = 0;
				}
			}
		}

		if (down) {
			dy += acc;
			if (dy > maxSpeed) {
				dy = maxSpeed;
			}
		} else {
			if (dy > 0) {
				dy -= deacc;
				if (dy < 0) {
					dy = 0;
				}
			}
		}

		if (left) {
			dx -= acc;
			if (dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		} else {
			if (dx < 0) {
				dx += deacc;
				if (dx > 0) {
					dx = 0;
				}
			}
		}

		if (right) {
			dx += acc;
			if (dx > maxSpeed) {
				dx = maxSpeed;
			}
		} else {
			if (dx > 0) {
				dx -= deacc;
				if (dx < 0) {
					dx = 0;
				}
			}
		}
	}

	public int getEXP() {
		return EXP;
	}

	public float getStamina() {
		return nStamina;
	}

	public float getMaxStamina() {
		return maxStamina;
	}

	public void update(Enemy enemy) {
		super.update();
		/*long timeStartAttack;
		if (attack && !attacking) {
			timeStartAttack = System.currentTimeMillis();
			attacking = true;
			if (attacking) {
				if (timeStartAttack + 1000 < time) {
					System.out.println("attacking");
				}
				attacking = false;
			}
		}*/
		/*
		 * if(attack && hitBounds.collides(enemy.getBounds()) && attacking == false) {
		 * attacking = true; System.out.println(timeStartAttack + "\n" + time);
		 * if(timeStartAttack + 50 < time){ System.out.println("attack monster"); }
		 * 
		 * 
		 * attacking = false; }
		 */

		move();
		if (!bounds.collisionTile(dx, 0)) {
			PlayState.map.x += dx;
			pos.x += dx;
		}
		if (!bounds.collisionTile(0, dy)) {
			PlayState.map.y += dy;
			pos.y += dy;
		}
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.green);
		g.drawRect((int) (pos.getWorldVar().x + bounds.getxOffset()),
				(int) (pos.getWorldVar().y + bounds.getyOffset()) + 5, (int) bounds.getWidth(),
				(int) bounds.getHeight());

		if (attack) {
			g.setColor(Color.red);
			g.drawRect((int) (hitBounds.getPos().getWorldVar().x + hitBounds.getxOffset()),
					(int) (hitBounds.getPos().getWorldVar().y + hitBounds.getyOffset()), (int) hitBounds.getWidth(),
					(int) hitBounds.getHeight());

		}

		g.drawImage(ani.getImage(), (int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y), size, size, null);

	}

	public void input(MouseHandler mouse, KeyHandler key) {
		if (mouse.getButton() == 1) {
			System.out.println("Player: " + pos.x + ", " + pos.y);
		}

		if (key.up.down) {
			up = true;
		} else {
			up = false;
		}
		if (key.down.down) {
			down = true;
		} else {
			down = false;
		}
		if (key.left.down) {
			left = true;
		} else {
			left = false;
		}
		if (key.right.down) {
			right = true;
		} else {
			right = false;
		}
		if (key.attack.down && canAttack) {
			attack = true;

		} else {
			if (!attacking) {
				attack = false;
			}
		}

		if (key.shift.down && nStamina > 0) {
			if (nStamina > 0) {
				maxSpeed = 5f;
				nStamina -= 0.5f;
			}
		} else {
			maxSpeed = 4f;
			if (nStamina < 100) {
				nStamina += 0.1f;
			}
		}

		if (up && down) {
			up = false;
			down = false;
		}

		if (right && left) {
			right = false;
			left = false;
		}
	}

}
