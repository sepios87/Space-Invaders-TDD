package fr.unilim.iut.spaceinvaders.model;

import java.awt.Color;
import java.net.URL;

import spaceinvaders.utils.MissileException;

public class Vaisseau extends SpriteTireur {

	private final URL locImage = Main.class.getResource("/imagesSprite/vaisseau.png");
	
	public Vaisseau(Dimension dimension, Position positionOrigine, int vitesse) {
		super(dimension, positionOrigine, vitesse);
	}

	 public Missile tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) throws MissileException {
		return this.tirerUnMissile(dimensionMissile, vitesseMissile, this.ordonneeLaPlusBasse()-1, Color.blue);
	 }

	@Override
	public URL getLocImage() {
		return locImage;
	}
}