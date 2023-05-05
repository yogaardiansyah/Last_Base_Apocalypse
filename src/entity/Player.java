package entity;

import main.UiPanel;
import main.KeyHandler;

public class Player extends Entity {

	UiPanel up;
	KeyHandler keyH;
	
	public Player(UiPanel up, KeyHandler keyH) {
		this.up = up;
		this.keyH = keyH;
	}
	public void setDefaultValues() {
		x = 100;
		y = 100;
		speed = 4;
	}
}
