package fr.unilim.iut.spaceinvaders.model;

import java.awt.Color;

import spaceinvaders.utils.MissileException;

public class Envahisseur extends SpriteTireur{
	
	boolean retour = true;
	
	 public Envahisseur(Dimension dimensionEnvahisseur, Position positionOrigineEnvahisseur, int vitesseEnvahisseur, Color couleur) {
		 super(dimensionEnvahisseur, positionOrigineEnvahisseur, vitesseEnvahisseur, couleur);
	 }
	 
	 public Envahisseur(Dimension dimensionEnvahisseur, Position positionOrigineEnvahisseur, int vitesseEnvahisseur) {
		 this(dimensionEnvahisseur, positionOrigineEnvahisseur, vitesseEnvahisseur, Color.red);
	 }
	 
	 public void changerRetour() {
		 this.retour=!retour;
	 }
	 
	 public boolean getRetour() {
		 return retour;
	 }
	 
	 public Missile tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) throws MissileException {
		return this.tirerUnMissile(dimensionMissile, -vitesseMissile, Constante.TYPE_MISSILE_ENVAHISSEUR, this.ordonneeLaPlusHaute() + 1, Color.pink);
	 }

}
