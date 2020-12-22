package com.thanhdo.game.entity;

import java.awt.*;
import com.thanhdo.game.graphics.Sprite;
import com.thanhdo.game.states.PlayState;
import com.thanhdo.game.util.AABB;
import com.thanhdo.game.util.Vector2f;

public class Animal extends Entity {

    private AABB sense;
    private int radius;

    public Animal(Sprite sprite, Vector2f orgin, int size) {
        super(sprite, orgin, size);

        acc = 1f;
        maxSpeed = 2f;
        radius = 200;

        bounds.setWidth(42);
        bounds.setHeight(20);
        bounds.setxOffset(12);
        bounds.setyOffset(45);

        sense = new AABB(new Vector2f(orgin.x + size / 2 - radius / 2, orgin.y + size / 2 - radius / 2), radius);

    }

    public void move(Player player) {
        if (sense.colCircleBox(player.getBounds()) && !die) {
            if (pos.y > player.pos.y + 5) {
                dy -= acc;
                up = true;
                down = false;
                if (dy < -maxSpeed) {
                    dy = -maxSpeed;
                }
            } else if (pos.y < player.pos.y - 5) {
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

            if (pos.x > player.pos.x + 5) {
                dx -= acc;
                left = true;
                right = false;
                if (dx < -maxSpeed) {
                    dx = -maxSpeed;
                }
            } else if (pos.x < player.pos.x - 5) {
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
        if (bounds.collides(player.bounds)) {
            if (!die) {
                PlayState.score++;
            }
            die = true;
        }
        if(!die){
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
    }

    @Override
    public void render(Graphics2D g) {
        if (!die) {
            g.drawImage(ani.getImage(), (int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y), size, size, null);
        }
    }
}
