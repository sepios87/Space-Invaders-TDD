package fr.unilim.iut.spaceinvaders.model;

public class Envahisseur extends Sprite{
	
	boolean retour = true;
	
	 public Envahisseur(Dimension dimensionEnvahisseur, Position positionOrigineEnvahisseur, int vitesseEnvahisseur) {
		 super(dimensionEnvahisseur, positionOrigineEnvahisseur, vitesseEnvahisseur);
	 }
	 
	 public void changerRetour() {
		 this.retour=!retour;
	 }
	 
	 public boolean getRetour() {
		 return retour;
	 }

}
