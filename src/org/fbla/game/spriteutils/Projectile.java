package org.fbla.game.spriteutils;

import org.fbla.game.Bridge;
import org.fbla.game.boards.GameBoard;
import org.fbla.game.utils.DamageReason;
import org.fbla.game.utils.Direction;
import org.fbla.game.utils.Utils;

public class Projectile extends Entity implements Moveable {
	

	public int damage;
	public Direction direction;
	public int speed;
	public Entity shooter;
	
	
	public Projectile(int x, int y, Entity shooter) {
		super(x, y);
		this.shooter = shooter;
	}
	
	public void setDamage(int damage){
		this.damage = damage;
	}
	public int getDamage(){
		return damage;
	}
	public Direction getDirection(){
		return direction;
	}
	public Entity getShooter(){
		return shooter;
	}
	

	@Override
	public void move() {
		if(direction.equals(Direction.RIGHT))
			x = x+speed;
		if(direction.equals(Direction.LEFT))
			x = x-speed;
		
		if(x > 1000){
			remove();
		}
		if(x < 0){
			remove();
		}
		
		if(!shooter.getType().equals(SpriteType.PLAYER)){
			if(Utils.intersects(getPolygon(), Bridge.getPlayer().getPolygon())){
				Bridge.getPlayer().damage(damage, this, DamageReason.PROJECTILE);
			}
		}
		for(Sprite sprite : ((GameBoard)Bridge.getGame().getBoard()).sprites){
			if(!this.getPolygon().getBounds().intersects(sprite.getPolygon().getBounds())) continue;
			if(sprite instanceof Entity){
				Utils.broadcastMessage(sprite.getType() + "");
				if(!sprite.getType().equals(shooter.getType())){
					((Entity) sprite).damage(this.damage, this, DamageReason.PROJECTILE);	
				} else {
					continue;
				}
			}
			if(sprite.getSubType().equals(SpriteSubType.INTERACTABLE)){
				((Interactable) sprite).interact();
				this.remove();
				continue;
			}
			if(!sprite.getType().equals(SpriteType.LADDER))remove();
		}
	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enable() {
		// TODO Auto-generated method stub
		
	}

}
