package fr.unilim.iut.spaceinvaders.model;

import java.awt.Color;

import spaceinvaders.utils.MissileException;

public abstract class SpriteTireur extends Sprite {

	public SpriteTireur(Dimension dimension, Position positionOrigine, int vitesse, Color couleur) {
		super(dimension, positionOrigine, vitesse, couleur);
	}

	public Missile tirerUnMissile(Dimension dimensionMissile, int vitesseMissile, int ordonne, Color couleur) throws MissileException {

		if (dimensionMissile.longueur() > this.dimension.longueur())
			throw new MissileException("probleme missile");

		Position positionOrigineMissile = calculerLaPositionDeTirDuMissile(dimensionMissile, ordonne);

		return new Missile(dimensionMissile, positionOrigineMissile, vitesseMissile, couleur);
	}

	private Position calculerLaPositionDeTirDuMissile(Dimension dimensionMissile, int ordonnee) {
		int abscisseMilieuSprite = this.abscisseLaPlusAGauche() + (this.dimension.longueur() / 2);
		int abscisseOrigineMissile = abscisseMilieuSprite - (dimensionMissile.longueur() / 2);

		int ordonneeeOrigineMissile = ordonnee;
		Position positionOrigineMissile = new Position(abscisseOrigineMissile, ordonneeeOrigineMissile);
		return positionOrigineMissile;
	}

}
