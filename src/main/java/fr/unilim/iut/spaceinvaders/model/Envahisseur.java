package fr.unilim.iut.spaceinvaders.model;

import java.awt.Color;
import java.io.File;

import spaceinvaders.utils.MissileException;

public class Envahisseur extends SpriteTireur{
	
	private boolean retour = true;
	private final int positionXBase;
	private final File locImage1 = new File("./envahisseur.png");
	private final File locImage2 = new File("./envahisseur2.png");
	
	 public Envahisseur(Dimension dimensionEnvahisseur, Position positionOrigineEnvahisseur, int vitesseEnvahisseur) {
		 super(dimensionEnvahisseur, positionOrigineEnvahisseur, vitesseEnvahisseur);
		 this.positionXBase = positionOrigineEnvahisseur.abscisse();
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

	@Override
	public File getLocImage() {
		if (this.getPosition().abscisse()%(Constante.DISTANCE_ENVAHISSEUR_PARCOURS)>Constante.DISTANCE_ENVAHISSEUR_PARCOURS/2) return locImage1;
		else return locImage2;
	}

}
