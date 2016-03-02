package org.fbla.game.sprites;

import java.util.Random;

import org.fbla.game.Bridge;
import org.fbla.game.sprites.tools.Key;
import org.fbla.game.spriteutils.DoorState;
import org.fbla.game.spriteutils.Interactable;
import org.fbla.game.spriteutils.SpriteType;
import org.fbla.game.spriteutils.Tool;
import org.fbla.game.utils.Images;
import org.fbla.game.utils.Utils;

import res.Texture;

public class Door extends Interactable {
	
	DoorState state;
	int id;
	SpriteType type = SpriteType.DOOR;

    public Door(int x, int y, int id) {
        super(x, y);
        this.id = id;
        init();
    }
    
    @Override
    public SpriteType getType(){
    	return type;
    }

    private void init() {
    	loadImage(Texture.loadTexture("objects/door.png"));
    	setImageDimensions(30, 45);
    	Images.colorDoor(getImage(),this);
    }
    
    @Override
    public void interact(){
    	Utils.displayMessage(new Random().nextInt(), "You don't have the key to open this door equipt.",
    			(Bridge.getGameBoardSize(0)/2) - (Bridge.getGame().getGraphics().getFontMetrics().stringWidth("You don't have the key to open this door equipt.")/2),
    			(Bridge.getGameBoardSize(1))/2, 20, "#FFFFFF", 15);
    }
    
    @Override
    public void interact(Tool tool){
    	if(type == SpriteType.OPEN_DOOR){
    		return;
    	}
    	if(tool instanceof Key){
    		if(((Key) tool).getID() == id || ((Key) tool).getID() == -1){
    			type = SpriteType.OPEN_DOOR;
    			loadImage(Texture.loadTexture("objects/door_open.png"));
    	    	setImageDimensions(30, 45);
    	    	Images.colorDoor(getImage(), this);
    	    	if(((Key) tool).getID() != -1){
    	    		Bridge.getPlayer().removeTool(tool);
    	    	}
    		}
    	}
    }

	public int getID() {
		return id;
	}
}