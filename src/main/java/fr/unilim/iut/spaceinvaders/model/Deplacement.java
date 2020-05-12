package fr.unilim.iut.spaceinvaders.model;

public class Deplacement {
	
	private final int longueur, hauteur;
	
	public Deplacement(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
	}
	
	public void deplacerSpriteVersLaDroite(Sprite sprite) {
		if (sprite.abscisseLaPlusADroite() < (this.longueur - 1)) {
			sprite.deplacerHorizontalementVers(Direction.DROITE);
			if (!estDansEspaceJeu(sprite.abscisseLaPlusADroite(), sprite.ordonneeLaPlusHaute())) {
				sprite.positionner(longueur - sprite.getDimension().longueur(), sprite.ordonneeLaPlusHaute());
			}
		}
	}

	public void deplacerSpriteVersLaGauche(Sprite sprite) {
		if (0 < sprite.abscisseLaPlusAGauche())
			sprite.deplacerHorizontalementVers(Direction.GAUCHE);
		if (!estDansEspaceJeu(sprite.abscisseLaPlusAGauche(), sprite.ordonneeLaPlusHaute())) {
			sprite.positionner(0, sprite.ordonneeLaPlusHaute());
		}
	}
	
	public void deplacementEnvahisseur(Envahisseur envahisseur) {
		if (envahisseur.getRetour())
			this.deplacerSpriteVersLaDroite(envahisseur);
		else
			this.deplacerSpriteVersLaGauche(envahisseur);

		if (!envahisseur.restePermimetreCourse()) envahisseur.changerRetour();
	}
	
	public boolean estDansEspaceJeu(int x, int y) {
		return (((x >= 0) && (x < this.longueur)) && ((y >= 0) && (y < this.hauteur)));
	}

}
