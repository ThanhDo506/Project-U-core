package com.thanhdo.game.states;

import java.awt.*;

import com.thanhdo.game.util.*;

public abstract class GameState
{
	private GameStateManager gsm;

	public GameState(GameStateManager gsm){
		this.gsm = gsm;
	}

	public abstract void update();	public abstract void input(MouseHandler mouse, KeyHandler key);
	public abstract void render(Graphics2D g);
}
