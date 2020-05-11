package fr.unilim.iut.spaceinvaders.model;

import java.awt.Color;

import spaceinvaders.utils.MissileException;

public class Vaisseau extends SpriteTireur {

	
	public Vaisseau(Dimension dimension, Position positionOrigine, int vitesse, Color couleur) {
		super(dimension, positionOrigine, vitesse, couleur);
	}
	
	public Vaisseau(Dimension dimension, Position positionOrigine, int vitesse) { 
		this(dimension, positionOrigine, vitesse, Color.gray);
	}

	 public Missile tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) throws MissileException {
		return this.tirerUnMissile(dimensionMissile, vitesseMissile, this.ordonneeLaPlusBasse()-1, Color.blue);
	 }
}