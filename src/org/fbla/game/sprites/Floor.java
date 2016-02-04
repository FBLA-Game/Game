package org.fbla.game.sprites;

import org.fbla.game.spriteutils.Sprite;
import org.fbla.game.spriteutils.SpriteType;

public class Floor extends Sprite {

    public Floor(int x, int y) {
        super(x, y);
        
        initFloor();
    }
    
    @Override
    public SpriteType getType(){
    	return SpriteType.FLOOR;
    }

    private void initFloor() {
        
        loadImage("objects/floor.png");
        getImageDimensions();
    }
}