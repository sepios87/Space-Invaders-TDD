package fr.unilim.iut.spaceinvaders.model;

public class Collision {
	
	private SpaceInvaders jeu;

	public Collision(SpaceInvaders jeu) {
		this.jeu = jeu;
	}

	public boolean detecterCollision(Sprite spriteLance, Sprite spriteTouche) {
		return spriteLance.ordonneeLaPlusHaute() >= spriteTouche.ordonneeLaPlusBasse()
				&& spriteLance.ordonneeLaPlusHaute() <= spriteTouche.ordonneeLaPlusHaute()
				&& spriteLance.abscisseLaPlusADroite() >= spriteTouche.abscisseLaPlusAGauche()
				&& spriteLance.abscisseLaPlusADroite() <= spriteTouche.abscisseLaPlusADroite();
	}
	
	public void gestionCollision() {
		this.collisionMissileEnvahisseur_MissileEnvahisseur();
		this.collisionMissileVaisseau();
	}
	
	public void collisionMissileEnvahisseur_MissileEnvahisseur() {
		for (int i = 0; i < jeu.recupererMissilesVaisseau().size(); i++) {
			boolean removeMissile = false;
			for (int j = 0; j < jeu.recupererEnvahisseurs().size(); j++) {
				if (this.detecterCollision(jeu.recupererMissilesVaisseau().get(i),
						jeu.recupererEnvahisseurs().get(j))) {
					jeu.removeEnvahisseur(j);
					jeu.augmenterScore();
					removeMissile = true;
				}
			}
				for (int j = 0; j < jeu.recupererMissilesEnvahisseur().size(); j++) {
					if (this.detecterCollision(jeu.recupererMissilesEnvahisseur().get(j),
							jeu.recupererMissilesVaisseau().get(i))) {
						jeu.removeMissileEnvahisseur(j);
						removeMissile = true;
					}
				}
			if (jeu.recupererMissilesVaisseau().get(i).getPosition().ordonnee()
							- jeu.recupererMissilesVaisseau().get(i).getDimension().hauteur() < 0)
				removeMissile = true;
			if (removeMissile) jeu.removeMissileVaisseau(i);
		}
	}
	
	public void collisionMissileVaisseau() {
		int i = 0;
		while (i < jeu.recupererMissilesEnvahisseur().size() && jeu.recupererVaisseau() != null) {
			if (this.detecterCollision(jeu.recupererMissilesEnvahisseur().get(i), jeu.recupererVaisseau()))
				jeu.removeVaisseau();

			if (jeu.recupererMissilesEnvahisseur().get(i).getPosition().ordonnee() > jeu.getHauteur())
				jeu.recupererMissilesEnvahisseur().remove(i);

			i++;
		}
	}
}
