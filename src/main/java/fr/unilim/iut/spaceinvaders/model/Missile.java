package fr.unilim.iut.spaceinvaders.model;

import java.awt.Color;

public class Missile extends Sprite{
	
	private char type;
	
	 public Missile(Dimension dimensionMissile, Position positionOrigineMissile, int vitesseMissile, char type, Color couleur) {
		 super(dimensionMissile, positionOrigineMissile, vitesseMissile, couleur);
		 this.type = type;
	 }
	 
	 public Missile(Dimension dimensionMissile, Position positionOrigineMissile, int vitesseMissile, char type) {
		 this (dimensionMissile, positionOrigineMissile, vitesseMissile, type, Color.blue);
	 }
	 
	 public char getType() {
		 return this.type;
	 }

}
