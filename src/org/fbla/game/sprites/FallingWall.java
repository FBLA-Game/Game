package org.fbla.game.sprites;

import java.util.Timer;
import java.util.TimerTask;

import org.fbla.game.spriteutils.Entity;
import org.fbla.game.spriteutils.Moveable;
import org.fbla.game.spriteutils.SpriteType;
import org.fbla.game.spriteutils.State;

public class FallingWall extends Entity implements Moveable {

	public boolean t;
	State state;
	int size;

	public FallingWall(int x, int y, int size, State state) {
		super(x, y);
		this.state = state;
		this.size = size;
		init();
	}

	@Override
	public SpriteType getType() {
		return SpriteType.FALLING_WALL;
	}

	private void init() {

		switch (state) {
		case VERTICAL:
			loadImage("wall/falling_vertical.png");
			height = size;
			setImageDimensions(2, size);
			break;
		case HORIZONTAL:
			loadImage("wall/falling_horizontal.png");
			setImageDimensions(size, 2);
			width = size;
			break;
		default:
			loadImage("wall/falling_vertical.png");
			break;
		}
	}

	public void startFalling() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				t = true;
			}
		}, 750);
	}

	@Override
	public void move() {

		if (t) {
			y = y + 2;
		}
	}

	@Override
	public void disable() {
		// disabled = true;
	}

	@Override
	public void enable() {

	}
}
