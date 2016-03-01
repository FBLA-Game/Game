package org.fbla.game.sprites;

import java.util.Timer;
import java.util.TimerTask;

import org.fbla.game.spriteutils.Entity;
import org.fbla.game.spriteutils.Moveable;
import org.fbla.game.spriteutils.SpriteType;

public class FallingFloor extends Entity implements Moveable {
	
	public boolean t;

    public FallingFloor(int x, int y) {
        super(x, y);
        initFloor();
    }
    
    @Override
    public SpriteType getType(){
    	return SpriteType.FALLING_FLOOR;
    }

    private void initFloor() {
        
        loadImage("objects/falling-floor.png");
        getImageDimensions();
    }
    
    public void startFalling(){
    	Timer timer = new Timer();
    	timer.schedule( new TimerTask() {
    		@Override
    		public void run() {
    			t = true;
    			}
    		}, 500);
    	}

	@Override
	public void move() {
		
		if(t){
			y = y+1;
		}
	}

	@Override
	public void disable() {
//		disabled = true;
	}

	@Override
	public void enable() {
		
	}
}