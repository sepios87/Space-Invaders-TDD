package fr.unilim.iut.spaceinvaders.model;

import java.awt.Color;

public class Missile extends Sprite{
	
	Color couleurMissile;
	
	 public Missile(Dimension dimensionMissile, Position positionOrigineMissile, int vitesseMissile, Color couleurMissile) {
		 super(dimensionMissile, positionOrigineMissile, vitesseMissile);
		 this.couleurMissile = couleurMissile;
	 }
	 
	 public Color getColor() {
		 return this.couleurMissile;
	 }
}
