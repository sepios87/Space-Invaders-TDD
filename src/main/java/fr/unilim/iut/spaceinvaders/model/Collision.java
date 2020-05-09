package fr.unilim.iut.spaceinvaders.model;

public class Collision {

	public Collision() {

	}

	public boolean detecterCollision(Missile missile, Envahisseur envahisseur) {
		return missile.getPosition().ordonnee() >= envahisseur.getPosition().ordonnee()
				&& missile.getPosition().ordonnee() - missile.getDimension().hauteur() <= envahisseur.getPosition()
						.ordonnee()
				&& missile.getPosition().abscisse() >= envahisseur.getPosition().abscisse() - envahisseur.getDimension().longueur()
				&& missile.getPosition().abscisse() - missile.getDimension().longueur() <= envahisseur.getPosition()
						.abscisse() ;
	}

}
