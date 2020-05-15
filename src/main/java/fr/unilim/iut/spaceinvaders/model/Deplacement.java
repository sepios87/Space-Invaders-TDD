package fr.unilim.iut.spaceinvaders.model;

public class Deplacement {
	
	private final SpaceInvaders jeu;
	
	public Deplacement(SpaceInvaders jeu) {
		this.jeu = jeu;
	}
	
	public void deplacerSpriteVersLaDroite(Sprite sprite) {
		if (sprite.abscisseLaPlusADroite() < (this.jeu.getLongueur() - 1)) {
			sprite.deplacerHorizontalementVers(Direction.DROITE);
			if (!estDansEspaceJeu(sprite.abscisseLaPlusADroite(), sprite.ordonneeLaPlusHaute())) {
				sprite.positionner(this.jeu.getLongueur() - sprite.getDimension().longueur(), sprite.ordonneeLaPlusHaute());
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

	public void deplacementEnvahisseur(int distanceParcours) {
		for (int i = 0; i<this.jeu.recupererEnvahisseurs().size(); i++) {
			if (this.jeu.recupererEnvahisseurs().get(i) != null) 
				if (this.jeu.recupererEnvahisseurs().get(i).getRetour())
					this.deplacerSpriteVersLaDroite(this.jeu.recupererEnvahisseurs().get(i));
				else
					this.deplacerSpriteVersLaGauche(this.jeu.recupererEnvahisseurs().get(i));

				if (!this.jeu.recupererEnvahisseurs().get(i).restePermimetreCourse(distanceParcours)) {
					this.jeu.recupererEnvahisseurs().get(i).changerRetour();
					if (this.jeu.recupererEnvahisseurs().get(i).ordonneeLaPlusBasse() < this.jeu.getHauteur() - Constante.VAISSEAU_HAUTEUR*2) {
						this.jeu.recupererEnvahisseurs().get(i).deplacerVerticalementVers(Direction.HAUT, 5*Constante.ENVAHISSEUR_VITESSE_BAS);
					}
					else jeu.removeVaisseau();;
				}
			}
		}
	
	public void deplacerMissile() {
		for (Missile missile : jeu.recupererMissilesVaisseau()) {
			missile.deplacerVerticalementVers(Direction.BAS);
		}
		for (Missile missile : jeu.recupererMissilesEnvahisseur()) {
			missile.deplacerVerticalementVers(Direction.HAUT);
		}
	}
	
	public boolean estDansEspaceJeu(int x, int y) {
		return (((x >= 0) && (x < this.jeu.getLongueur())) && ((y >= 0) && (y < this.jeu.getHauteur())));
	}

}
