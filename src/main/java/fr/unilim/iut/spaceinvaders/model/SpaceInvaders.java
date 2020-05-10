package fr.unilim.iut.spaceinvaders.model;

import java.util.ArrayList;
import java.util.List;

import fr.unilim.iut.spaceinvaders.moteurjeu.Commande;
import fr.unilim.iut.spaceinvaders.moteurjeu.Jeu;
import spaceinvaders.utils.DebordementEspaceJeuException;
import spaceinvaders.utils.HorsEspaceJeuException;
import spaceinvaders.utils.MissileException;

public class SpaceInvaders implements Jeu {

	private int longueur, hauteur, score = 0;
	private Vaisseau vaisseau;
	private List<Envahisseur> envahisseurs = new ArrayList<Envahisseur>();
	private List<Missile> missiles = new ArrayList<Missile>();
	private Collision collision = new Collision();

	public int getScore() {
		return this.score;
	}

	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
	}

	public SpaceInvaders() {
		this(Constante.ESPACEJEU_LONGUEUR, Constante.ESPACEJEU_HAUTEUR);
	}

	public void initialiserJeu() {
		positionnerUnNouveauVaisseau(new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR),
				new Position(this.longueur / 2, this.hauteur - 1), Constante.VAISSEAU_VITESSE);
		while (this.envahisseurs.size() < 10)
			positionnerUnNouveauEnvahisseur(
					new Dimension(Constante.ENVAHISSEUR_LONGUEUR, Constante.ENVAHISSEUR_HAUTEUR),
					new Position((int) (Math.random() * (longueur - Constante.ENVAHISSEUR_LONGUEUR)),
							(int) (Math.random()
									* (hauteur - Constante.ENVAHISSEUR_HAUTEUR - Constante.VAISSEAU_HAUTEUR * 2)
									+ Constante.ENVAHISSEUR_HAUTEUR)),
					Constante.ENVAHISSEUR_VITESSE);
	}

	// -----------------Sprite--------------------

	public void deplacerSpriteVersLaDroite(Sprite sprite) {
		if (sprite.abscisseLaPlusADroite() < (longueur - 1)) {
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

	private boolean aUnSpriteQuiOccupeLaPosition(int x, int y, Sprite sprite) {
		return this.aUnSprite(sprite) && sprite.occupeLaPosition(x, y);
	}

	public boolean aUnSprite(Sprite sprite) {
		return sprite != null;
	}

	// -----------------Vaisseau-------------------

	public void positionnerUnNouveauVaisseau(Dimension dimension, Position position, int vitesse) {
		controlerEspaceJeu(dimension, position);
		vaisseau = new Vaisseau(dimension, position, vitesse);
	}

	public Vaisseau recupererVaisseau() {
		return this.vaisseau;
	}

	// -----------------Envahisseur----------------

	public void positionnerUnNouveauEnvahisseur(Dimension dimension, Position position, int vitesse) {
		controlerEspaceJeu(dimension, position);
		boolean superpose = false;
		int i = 0;
		while (i < this.envahisseurs.size() && !superpose) {
			superpose = this.estSuperpose(new Envahisseur(dimension, position, vitesse), this.envahisseurs.get(i));
			i++;
		}
		if (!superpose)
			this.envahisseurs.add(new Envahisseur(dimension, position, vitesse));
	}

	public List<Envahisseur> recupererEnvahisseurs() {
		return this.envahisseurs;
	}

	// -----------------Missile--------------------

	public void deplacerMissile() {
		for (Missile missile : this.missiles) {
			missile.deplacerVerticalementVers(Direction.HAUT_ECRAN);
		}
	}

	public List<Missile> recupererMissiles() {
		return this.missiles;
	}

	public void tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {
		if ((vaisseau.dimension.hauteur() + dimensionMissile.hauteur()) > this.hauteur)
			throw new MissileException(
					"Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");
		if (this.missiles.isEmpty())
			this.missiles.add(this.vaisseau.tirerUnMissile(dimensionMissile, vitesseMissile));
		else {
			if (!this.estSuperpose(this.missiles.get(this.missiles.size() - 1), this.vaisseau))
				this.missiles.add(this.vaisseau.tirerUnMissile(dimensionMissile, vitesseMissile));
		}
	}
		
	/*public void tirerMissileEnvahisseur(Dimension dimensionMissile, int vitesseMissile) {
		if ((this.envahisseurs.get(0).dimension.hauteur() + dimensionMissile.hauteur()) > this.hauteur)
			throw new MissileException(
					"Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");
		if (this.missiles.isEmpty())
			this.missiles.add(this.envahisseurs.get(0).tirerUnMissile(dimensionMissile, -vitesseMissile));
		else {
			if (!this.estSuperpose(this.missiles.get(this.missiles.size() - 1), this.envahisseurs.get(0)))
				this.missiles.add(this.envahisseurs.get(0).tirerUnMissile(dimensionMissile, vitesseMissile));
		}
	}*/
	// -----------------Jeu-------------------------

	@Override
	public void evoluer(Commande commandeUser) {

		if (commandeUser.gauche) {
			deplacerSpriteVersLaGauche(this.vaisseau);
			/*if (Math.random() > 0.5 && !this.envahisseurs.isEmpty())
				this.tirerMissileEnvahisseur(new Dimension(Constante.MISSILE_LONGUEUR, Constante.MISSILE_HAUTEUR),
					Constante.MISSILE_VITESSE);*/
		}
			

		if (commandeUser.droite)
			deplacerSpriteVersLaDroite(this.vaisseau);

		if (commandeUser.tir) {
			this.tirerUnMissile(new Dimension(Constante.MISSILE_LONGUEUR, Constante.MISSILE_HAUTEUR),
					Constante.MISSILE_VITESSE);
		}

		if (!this.missiles.isEmpty() && commandeUser != null) {
			this.deplacerMissile();
			for (int i = 0; i < this.missiles.size(); i++) {
				for (int j = 0; j < this.envahisseurs.size(); j++) {
					if (this.envahisseurs.get(j) != null
							&& collision.detecterCollision(this.missiles.get(i), this.envahisseurs.get(j))) {
						this.envahisseurs.remove(j);
						score++;
					}
				}
				if (this.missiles.get(i).getPosition().ordonnee()
						+ this.missiles.get(i).getDimension().hauteur() > this.hauteur)
					this.missiles.remove(i);

				if (this.missiles.get(i).getPosition().ordonnee() + this.missiles.get(i).dimension.hauteur() < 0) {
					this.missiles.remove(i);
				}
			}
		}

		for (Envahisseur envahisseur : this.envahisseurs) {
			if (this.aUnSprite(envahisseur) && commandeUser != null) {
				if (envahisseur.getRetour())
					this.deplacerSpriteVersLaDroite(envahisseur);
				else
					this.deplacerSpriteVersLaGauche(envahisseur);
				if (envahisseur.getPosition().abscisse() + envahisseur.getDimension().longueur() > this.longueur - 2)
					envahisseur.changerRetour();
				if (envahisseur.getPosition().abscisse() < 1)
					envahisseur.changerRetour();
			}
		}

	}

	public boolean estSuperpose(Sprite sprite1, Sprite sprite2) {
		return !((sprite2.getPosition().abscisse() > sprite1.getPosition().abscisse()
				+ sprite1.getDimension().longueur()
				|| sprite2.getPosition().abscisse() + sprite2.getDimension().longueur() < sprite1.getPosition()
						.abscisse())
				&& (sprite2.getPosition().ordonnee() < sprite1.getPosition().ordonnee()
						+ sprite1.getDimension().hauteur()
						|| sprite2.getPosition().ordonnee() + sprite2.getDimension().hauteur() > sprite1.getPosition()
								.ordonnee()));
	}

	private char recupererMarqueDeLaPosition(int x, int y) {
		char marque = Constante.MARQUE_VIDE;
		if (this.aUnSpriteQuiOccupeLaPosition(x, y, this.vaisseau))
			marque = Constante.MARQUE_VAISSEAU;
		else if (!this.missiles.isEmpty()) {
			int i = 0;
			while (i < this.missiles.size() && marque == Constante.MARQUE_VIDE) {
				if (this.aUnSpriteQuiOccupeLaPosition(x, y, this.missiles.get(i)))
					marque = Constante.MARQUE_MISSILE;
				i++;
			}
		} else if (!this.envahisseurs.isEmpty()) {
			int i = 0;
			while (i < this.envahisseurs.size() && marque == Constante.MARQUE_VIDE) {
				if (this.aUnSpriteQuiOccupeLaPosition(x, y, this.envahisseurs.get(i)))
					marque = Constante.MARQUE_ENVAHISSEUR;
				i++;
			}
		}
		return marque;
	}

	public String recupererEspaceJeuDansChaineASCII() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < longueur; x++) {
				espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
			}
			espaceDeJeu.append(Constante.MARQUE_FIN_LIGNE);
		}
		return espaceDeJeu.toString();
	}

	private boolean estDansEspaceJeu(int x, int y) {
		return (((x >= 0) && (x < this.longueur)) && ((y >= 0) && (y < this.hauteur)));
	}

	private void controlerEspaceJeu(Dimension dimension, Position position) {

		if (!estDansEspaceJeu(position.abscisse(), position.ordonnee()))
			throw new HorsEspaceJeuException("La sprite est en dehors de l'espace jeu a cause de sa position");

		if (!estDansEspaceJeu(position.abscisse() + dimension.longueur() - 1, position.ordonnee()))
			throw new DebordementEspaceJeuException(
					"Le sprite déborde de l'espace jeu vers la droite à cause de sa longueur");

		if (!estDansEspaceJeu(position.abscisse(), position.ordonnee() - dimension.hauteur() + 1))
			throw new DebordementEspaceJeuException(
					"Le sprite déborde de l'espace jeu vers le bas à cause de sa hauteur : ");
	}

	@Override
	public boolean etreFini() {
		return this.envahisseurs.isEmpty() && this.missiles.isEmpty();
	}

}