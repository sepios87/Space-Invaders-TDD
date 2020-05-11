package fr.unilim.iut.spaceinvaders.model;

import java.awt.Color;

import spaceinvaders.utils.MissileException;

public class Envahisseur extends SpriteTireur{
	
	private boolean retour = true;
	private int positionXBase;
	
	
	 public Envahisseur(Dimension dimensionEnvahisseur, Position positionOrigineEnvahisseur, int vitesseEnvahisseur, Color couleur) {
		 super(dimensionEnvahisseur, positionOrigineEnvahisseur, vitesseEnvahisseur, couleur);
		 this.positionXBase = positionOrigineEnvahisseur.abscisse();
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
	 
	 public boolean restePermimetreCourse() {
		 return !(positionXBase < this.getPosition().abscisse()-Constante.DISTANCE_ENVAHISSEUR_PARCOURS || positionXBase >= this.getPosition().abscisse() + Constante.DISTANCE_ENVAHISSEUR_PARCOURS);
	 }
	 
	 public Missile tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) throws MissileException {
		return this.tirerUnMissile(dimensionMissile, vitesseMissile, this.ordonneeLaPlusHaute() + 1, Color.pink);
	 }

}
