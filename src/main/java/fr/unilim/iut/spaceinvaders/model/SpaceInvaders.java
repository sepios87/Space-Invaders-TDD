package fr.unilim.iut.spaceinvaders.model;

import fr.unilim.iut.spaceinvaders.moteurjeu.Commande;
import fr.unilim.iut.spaceinvaders.moteurjeu.Jeu;
import spaceinvaders.utils.DebordementEspaceJeuException;
import spaceinvaders.utils.HorsEspaceJeuException;
import spaceinvaders.utils.MissileException;

public class SpaceInvaders implements Jeu {

	int longueur;
	int hauteur;
	private Vaisseau vaisseau;
	private Missile missile;
	private Envahisseur envahisseur;

	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
	}

	public void initialiserJeu() {
		positionnerUnNouveauVaisseau(new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR), new Position(this.longueur / 2, this.hauteur - 1), Constante.VAISSEAU_VITESSE);
		positionnerUnNouveauEnvahisseur(new Dimension(Constante.ENVAHISSEUR_LONGUEUR, Constante.ENVAHISSEUR_HAUTEUR), new Position(longueur / 2, hauteur / 2), Constante.ENVAHISSEUR_VITESSE);
	}

	public void positionnerUnNouveauVaisseau(Dimension dimension, Position position, int vitesse) {

		int x = position.abscisse();
		int y = position.ordonnee();

		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");

		int longueurVaisseau = dimension.longueur();
		int hauteurVaisseau = dimension.hauteur();

		if (!estDansEspaceJeu(x + longueurVaisseau - 1, y))
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - hauteurVaisseau + 1))
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers le bas à cause de sa hauteur");

		vaisseau = new Vaisseau(dimension, position, vitesse);
	}

	public void positionnerUnNouveauEnvahisseur(Dimension dimension, Position position, int vitesse) {
		int x = position.abscisse();
		int y = position.ordonnee();

		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position de l'envahisseur est en dehors de l'espace jeu");

		int longueurEnvahisseur = dimension.longueur();
		int hauteurEnvahisseur = dimension.hauteur();

		if (!estDansEspaceJeu(x + longueurEnvahisseur - 1, y))
			throw new DebordementEspaceJeuException(
					"L'envahisseur déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - hauteurEnvahisseur + 1))
			throw new DebordementEspaceJeuException(
					"L'envahisseur déborde de l'espace jeu vers le bas à cause de sa hauteur");
		
		envahisseur = new Envahisseur(dimension, position, vitesse);
	}

	private boolean estDansEspaceJeu(int x, int y) {
		return (((x >= 0) && (x < longueur)) && ((y >= 0) && (y < hauteur)));
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

	private char recupererMarqueDeLaPosition(int x, int y) {
		char marque;
		if (this.aUnVaisseauQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_VAISSEAU;
		else if (this.aUnMissileQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_MISSILE;
		else if (this.aUnEnvahisseurQuiOccupeLaPosition(x, y)) {
			marque = Constante.MARQUE_ENVAHISSEUR;
		} else
			marque = Constante.MARQUE_VIDE;
		return marque;
	}

	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return this.aUnVaisseau() && vaisseau.occupeLaPosition(x, y);
	}

	private boolean aUnMissileQuiOccupeLaPosition(int x, int y) {
		return this.aUnMissile() && missile.occupeLaPosition(x, y);
	}

	private boolean aUnEnvahisseurQuiOccupeLaPosition(int x, int y) {
		return this.aUnEnvahisseur() && envahisseur.occupeLaPosition(x, y);
	}

	public boolean aUnVaisseau() {
		return vaisseau != null;
	}

	public boolean aUnMissile() {
		return missile != null;
	}

	public boolean aUnEnvahisseur() {
		return envahisseur != null;
	}

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

	public void tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {

		if ((vaisseau.dimension.hauteur() + dimensionMissile.hauteur()) > this.hauteur)
			throw new MissileException(
					"Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");

		this.missile = this.vaisseau.tirerUnMissile(dimensionMissile, vitesseMissile);
	}

	public Vaisseau recupererVaisseau() {
		return this.vaisseau;
	}

	public Missile recupererMissile() {
		return this.missile;
	}

	public Envahisseur recupererEnvahisseur() {
		return this.envahisseur;
	}

	@Override
	public void evoluer(Commande commandeUser) {

		if (commandeUser.gauche) {
			deplacerSpriteVersLaGauche(this.vaisseau);
		}

		if (commandeUser.droite) {
			deplacerSpriteVersLaDroite(this.vaisseau);
		}

		if (commandeUser.tir && !this.aUnMissile()) {
			this.tirerUnMissile(new Dimension(Constante.MISSILE_LONGUEUR, Constante.MISSILE_HAUTEUR),
					Constante.MISSILE_VITESSE);
		}

		if (this.aUnMissile() && commandeUser != null) {
			this.deplacerMissile();
			if (this.missile.origine.ordonnee() + this.missile.dimension.hauteur() < 0) {
				this.missile = null;
			}
		}

		if (this.aUnEnvahisseur() && commandeUser != null) {
			if (this.envahisseur.getRetour())
				this.deplacerSpriteVersLaDroite(this.envahisseur);
			else
				this.deplacerSpriteVersLaGauche(this.envahisseur);
			if (this.envahisseur.origine.abscisse() + this.envahisseur.getDimension().longueur() > this.longueur - 2 )
				this.envahisseur.changerRetour();
			if (this.envahisseur.origine.abscisse() < 1)
				this.envahisseur.changerRetour();
		}

	}

	@Override
	public boolean etreFini() {
		return false;
	}

	public void deplacerMissile() {
		// TODO Auto-generated method stub
		this.missile.deplacerVerticalementVers(Direction.HAUT_ECRAN);
	}

}