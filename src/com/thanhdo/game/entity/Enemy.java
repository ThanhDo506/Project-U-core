package com.thanhdo.game.entity;

import com.thanhdo.game.graphics.Font;
import com.thanhdo.game.graphics.Sprite;
import com.thanhdo.game.states.PlayState;
import com.thanhdo.game.util.AABB;
import com.thanhdo.game.util.Vector2f;

import java.awt.*;

public class Enemy extends Entity {
	private Font font;
	private AABB sense;
	private int radius;

	protected AABB attackrange;
	protected int r_attackrange;

	public Enemy(Sprite sprite, Vector2f orgin, int size, String name) {
		super(sprite, orgin, size);
		acc = 1f;
		maxSpeed = 2f;
		radius = 350;

		attackrange = new AABB(new Vector2f(orgin.x + bounds.getxOffset() + bounds.getWidth() / 2 - r_attackrange / 2,
				orgin.y + bounds.getyOffset() + bounds.getHeight() / 2 - r_attackrange / 2), r_attackrange);
		bounds.setWidth(42);
		bounds.setHeight(20);
		bounds.setxOffset(12);
		bounds.setyOffset(45);

		sense = new AABB(new Vector2f(orgin.x + size / 2 - radius / 2, orgin.y + size / 2 - radius / 2), radius);
	}

	public void move(Player player) {
		if (sense.colCircleBox(player.getBounds()) && !attackrange.colCircleBox(player.getBounds())) { // sense.colCircleBox(playerBounds)
																										// &&
																										// !attackrange.colCircleBox(playerBounds)
			if (pos.y > player.pos.y + 25) {
				dy -= acc;
				up = true;
				down = false;
				if (dy < -maxSpeed) {
					dy = -maxSpeed;
				}
			} else if (pos.y < player.pos.y - 25) {
				dy += acc;
				down = true;
				up = false;
				if (dy > maxSpeed) {
					dy = maxSpeed;
				}
			} else {
				dy = 0;
				up = false;
				down = false;
			}

			if (pos.x > player.pos.x + 25) {
				dx -= acc;
				left = true;
				right = false;
				if (dx < -maxSpeed) {
					dx = -maxSpeed;
				}
			} else if (pos.x < player.pos.x - 25) {
				dx += acc;
				right = true;
				left = false;
				if (dx > maxSpeed) {
					dx = maxSpeed;
				}
			} else {
				dx = 0;
				right = false;
				left = false;
			}
		} else {
			up = false;
			down = false;
			left = false;
			right = false;
			dx = 0;
			dy = 0;
		}
	}

	@Override
	public void render(Graphics2D g) {
		// Sprite.drawArray(g, font,"Lv."+ level + " " + name + " " + getPercentHP(),
		// new Vector2f(pos.getWorldVar().x,pos.getWorldVar().y - 25), 16, 32, 16, 0);
		// g.setColor(Color.green);
		// g.drawRect((int)(pos.getWorldVar().x + bounds.getxOffset()),
		// (int)(pos.getWorldVar().y + bounds.getyOffset()), (int)bounds.getWidth(),
		// (int)bounds.getHeight());

		g.setColor((Color.BLUE));
		g.drawOval((int) sense.getPos().getWorldVar().x, (int) sense.getPos().getWorldVar().y, radius, radius);

		// g.setColor(Color.red);
		// g.drawRect((int)(hitBounds.getPos().getWorldVar().x +
		// hitBounds.getxOffset()), (int)(hitBounds.getPos().getWorldVar().y +
		// hitBounds.getyOffset()), (int)hitBounds.getWidth(),
		// (int)hitBounds.getHeight());

		g.drawImage(ani.getImage(), (int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y), size, size, null);

		// g.setColor(Color.red);
		// g.drawRect((int)(hitBounds.getPos().getWorldVar().x +
		// hitBounds.getxOffset()), (int)(hitBounds.getPos().getWorldVar().y +
		// hitBounds.getyOffset()), (int)hitBounds.getWidth(),
		// (int)hitBounds.getHeight());

	}

	public void update(Player player) {
		super.update();

		attackPlayer(player);
		move(player);
		if (!bounds.collisionTile(dx, 0)) {
			sense.getPos().x += dx;
			pos.x += dx;
		}
		if (!bounds.collisionTile(0, dy)) {
			sense.getPos().y += dy;
			pos.y += dy;
		}
	}

	public void attackPlayer(Player player) {
		if (hitBounds.collides(player.getBounds())) {
			// System.out.println("Attacking player");
			player.setnHealth(1);
		}
	}

}
