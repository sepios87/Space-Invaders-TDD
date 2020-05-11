package fr.unilim.iut.spaceinvaders.model;

public class Collision {

	public Collision() {

	}

	public boolean detecterCollision(Sprite spriteLance, Sprite spriteTouche) {
		return spriteLance.ordonneeLaPlusHaute() >= spriteTouche.ordonneeLaPlusBasse()
				&& spriteLance.ordonneeLaPlusHaute() <= spriteTouche.ordonneeLaPlusHaute()
				&& spriteLance.abscisseLaPlusADroite() >= spriteTouche.abscisseLaPlusAGauche()
				&& spriteLance.abscisseLaPlusADroite() <= spriteTouche.abscisseLaPlusADroite();
	}

}
