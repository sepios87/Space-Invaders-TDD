package fr.unilim.iut.spaceinvaders;

public class Vaisseau extends Sprite {

	
	public Vaisseau(Dimension dimension, Position positionOrigine, int vitesse) {
		super(dimension, positionOrigine, vitesse);
	}

	 public Missile tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) throws MissileException {
		 
		 if (dimensionMissile.longueur() > this.dimension.longueur())
			 throw new MissileException ("probleme missile");
			 
			Position positionOrigineMissile = calculerLaPositionDeTirDuMissile(dimensionMissile);

			return new Missile(dimensionMissile, positionOrigineMissile, vitesseMissile);
		}

	private Position calculerLaPositionDeTirDuMissile(Dimension dimensionMissile) {
		int abscisseMilieuVaisseau = this.abscisseLaPlusAGauche() + (this.dimension.longueur() / 2);
		int abscisseOrigineMissile = abscisseMilieuVaisseau - (dimensionMissile.longueur() / 2);

		int ordonneeeOrigineMissile = this.ordonneeLaPlusBasse() - 1;
		Position positionOrigineMissile = new Position(abscisseOrigineMissile, ordonneeeOrigineMissile);
		return positionOrigineMissile;
	}

}