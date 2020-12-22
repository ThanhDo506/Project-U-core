package com.thanhdo.game.entity;

import java.awt.*;
import com.thanhdo.game.graphics.Sprite;
import com.thanhdo.game.states.PlayState;
import com.thanhdo.game.util.AABB;
import com.thanhdo.game.util.Vector2f;
import com.thanhdo.game.graphics.Font;

public class NPC extends Entity {

    private AABB sense;
    private int radius;
    private boolean canMove;
    private String message;
    private boolean toughtPlayer = false;;

    private AABB attackrange;
    private int r_attackrange;

    private Font font;

    public NPC(String name, Sprite sprite, Vector2f orgin, int size, boolean canMove) {
        super(sprite, orgin, size);

        this.name = name;
        this.canMove = canMove;
        message = "";

        acc = 1f;
        maxSpeed = 3.9f;
        radius = 750;

        attackrange = new AABB(new Vector2f(orgin.x + bounds.getxOffset() + bounds.getWidth() / 2 - r_attackrange / 2,
                orgin.y + bounds.getyOffset() + bounds.getHeight() / 2 - r_attackrange / 2), r_attackrange);

        bounds.setWidth(42);
        bounds.setHeight(20);
        bounds.setxOffset(12);
        bounds.setyOffset(45);

        sense = new AABB(new Vector2f(orgin.x + size / 2 - radius / 2, orgin.y + size / 2 - radius / 2), radius);
        font = new Font("font/ShoFont.png", 16, (int) (256 / 6));
    }

    public NPC(String name, Sprite sprite, Vector2f orgin, int size, boolean canMove, String message) {
        super(sprite, orgin, size);

        this.canMove = canMove;
        this.message = message;
        this.name = name;

        acc = 1f;
        maxSpeed = 3.9f;
        radius = 750;

        bounds.setWidth(42);
        bounds.setHeight(20);
        bounds.setxOffset(12);
        bounds.setyOffset(45);

        sense = new AABB(new Vector2f(orgin.x + size / 2 - radius / 2, orgin.y + size / 2 - radius / 2), radius);
        font = new Font("font/ShoFont.png", 16, (int) (256 / 6));
    }

    public void move(Player player) {
        if (sense.colCircleBox(player.getBounds()) && canMove) {
            if (pos.y > player.pos.y + 50) {
                dy -= acc;
                up = true;
                down = false;
                if (dy < -maxSpeed) {
                    dy = -maxSpeed;
                }
            } else if (pos.y < player.pos.y - 50) {
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

            if (pos.x > player.pos.x + 50) {
                dx -= acc;
                left = true;
                right = false;
                if (dx < -maxSpeed) {
                    dx = -maxSpeed;
                }
            } else if (pos.x < player.pos.x - 50) {
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

    public void update(Player player) {
        super.update();

        move(player);
        if (!bounds.collisionTile(dx, 0)) {
            sense.getPos().x += dx;
            pos.x += dx;
        }
        if (!bounds.collisionTile(0, dy)) {
            sense.getPos().y += dy;
            pos.y += dy;
        }
        if (hitBounds.collides(player.bounds)) {
            toughtPlayer = true;
        } else {
            toughtPlayer = false;
        }

    }

    @Override
    public void render(Graphics2D g) {
        Sprite.drawArray(g, font, name, new Vector2f(pos.getWorldVar().x, pos.getWorldVar().y - 25), 16, 32, 16, 0);
        g.drawImage(ani.getImage(), (int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y), size, size, null);
        if (toughtPlayer) {
            Sprite.drawArray(g, font, message, new Vector2f(pos.getWorldVar().x, pos.getWorldVar().y - 50), 16, 32, 16,
                    0);
        }
    }
}
