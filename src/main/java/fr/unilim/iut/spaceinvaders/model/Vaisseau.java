package fr.unilim.iut.spaceinvaders.model;

import java.awt.Color;
import java.io.File;

import spaceinvaders.utils.MissileException;

public class Vaisseau extends SpriteTireur {

	private final File locImage = new File("./vaisseau.png");
	
	public Vaisseau(Dimension dimension, Position positionOrigine, int vitesse) {
		super(dimension, positionOrigine, vitesse);
	}

	 public Missile tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) throws MissileException {
		return this.tirerUnMissile(dimensionMissile, vitesseMissile, this.ordonneeLaPlusBasse()-1, Color.blue);
	 }

	@Override
	public File getLocImage() {
		return locImage;
	}
}