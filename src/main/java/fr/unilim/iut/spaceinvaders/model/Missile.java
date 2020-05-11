package fr.unilim.iut.spaceinvaders.model;

import java.awt.Color;

public class Missile extends Sprite{
	
	 public Missile(Dimension dimensionMissile, Position positionOrigineMissile, int vitesseMissile, Color couleur) {
		 super(dimensionMissile, positionOrigineMissile, vitesseMissile, couleur);
	 }
	 
	 public Missile(Dimension dimensionMissile, Position positionOrigineMissile, int vitesseMissile) {
		 this (dimensionMissile, positionOrigineMissile, vitesseMissile, Color.blue);
	 }
}
