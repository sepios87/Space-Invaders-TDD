package fr.unilim.iut.spaceinvaders.model;

import spaceinvaders.utils.MissileException;

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
	 
	 public Missile tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) throws MissileException {
		 
		 if (dimensionMissile.longueur() > this.dimension.longueur())
			 throw new MissileException ("probleme missile");
			 
			Position positionOrigineMissile = calculerLaPositionDeTirDuMissile(dimensionMissile);

			return new Missile(dimensionMissile, positionOrigineMissile, vitesseMissile);
		}

	private Position calculerLaPositionDeTirDuMissile(Dimension dimensionMissile) {
		int abscisseMilieuEnvahisseur = this.abscisseLaPlusAGauche() + (this.dimension.longueur() / 2);
		int abscisseOrigineMissile = abscisseMilieuEnvahisseur - (dimensionMissile.longueur() / 2);

		int ordonneeeOrigineMissile = this.ordonneeLaPlusBasse() - 1;
		Position positionOrigineMissile = new Position(abscisseOrigineMissile, ordonneeeOrigineMissile);
		return positionOrigineMissile;
	}

}
