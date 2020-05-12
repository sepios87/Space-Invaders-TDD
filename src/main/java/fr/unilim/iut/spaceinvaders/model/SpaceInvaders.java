package fr.unilim.iut.spaceinvaders.model;

import java.util.ArrayList;
import java.util.List;

import fr.unilim.iut.spaceinvaders.moteurjeu.Commande;
import fr.unilim.iut.spaceinvaders.moteurjeu.Jeu;
import spaceinvaders.utils.DebordementEspaceJeuException;
import spaceinvaders.utils.HorsEspaceJeuException;
import spaceinvaders.utils.MissileException;

public class SpaceInvaders implements Jeu {

	private final int longueur, hauteur;
	private int score = 0, nbVague = 0;
	private long tpsEntreDeuxMissilesVaisseau = 0, tpsEntreDeuxMissilesEnvahisseur = 0;
	private Vaisseau vaisseau;
	private List<Envahisseur> envahisseurs = new ArrayList<Envahisseur>();
	private List<Missile> missilesVaisseau = new ArrayList<Missile>();
	private List<Missile> missilesEnvahisseur = new ArrayList<Missile>();
	private final Collision collision = new Collision();
	private final Deplacement deplacement;

	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
		deplacement = new Deplacement(longueur, hauteur);
	}

	public SpaceInvaders() {
		this(Constante.ESPACEJEU_LONGUEUR, Constante.ESPACEJEU_HAUTEUR);
	}

	public void initialiserJeu() {
		positionnerUnNouveauVaisseau(new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR),
				new Position(this.longueur / 2, this.hauteur - 1), Constante.VAISSEAU_VITESSE);
		this.genererLigneEnvahisseur(Constante.NB_ENVAHISSEUR_VAGUE_MINI, Constante.ESPACE_LONGEUR_ENTRE_ENVAHISSEURS);
	}

	// -----------------Sprite--------------------

	private boolean aUnSpriteQuiOccupeLaPosition(int x, int y, Sprite sprite) {
		return sprite != null && sprite.occupeLaPosition(x, y);
	}

	// -----------------Vaisseau-------------------

	public void positionnerUnNouveauVaisseau(Dimension dimension, Position position, int vitesse) {
		controlerEspaceJeu(dimension, position);
		vaisseau = new Vaisseau(dimension, position, vitesse);
	}

	// -----------------Envahisseur----------------

	public void positionnerUnNouveauEnvahisseur(Dimension dimension, Position position, int vitesse) {
		controlerEspaceJeu(dimension, position);
		if (this.envahisseurs.isEmpty())
			this.envahisseurs.add(new Envahisseur(dimension, position, vitesse));
		else
			this.envahisseurs.add(new Envahisseur(dimension, position, vitesse));
	}

	private void genererLigneEnvahisseur(int nbEnvahisseur, int espaceEntreEvahisseurs) {
		for (int j = 0; j < nbEnvahisseur; j++) {
			this.positionnerUnNouveauEnvahisseur(
					new Dimension(Constante.ENVAHISSEUR_LONGUEUR, Constante.ENVAHISSEUR_HAUTEUR),
					new Position(
							(j % nbMaxEnvahisseurParLigne())
									* (Constante.ENVAHISSEUR_LONGUEUR + Constante.ESPACE_LONGEUR_ENTRE_ENVAHISSEURS + Constante.DISTANCE_ENVAHISSEUR_PARCOURS) + Constante.DISTANCE_ENVAHISSEUR_PARCOURS,
							(j / (nbMaxEnvahisseurParLigne()) * Constante.ESPACE_HAUTEUR_ENTRE_ENVAHISSEUR)
									+ Constante.ENVAHISSEUR_HAUTEUR),
					Constante.ENVAHISSEUR_VITESSE);
		}
		nbVague++;
	}

	private int nbMaxEnvahisseurParLigne() {
		return Constante.ESPACEJEU_LONGUEUR
				/ (Constante.ENVAHISSEUR_LONGUEUR + Constante.ESPACE_LONGEUR_ENTRE_ENVAHISSEURS + Constante.DISTANCE_ENVAHISSEUR_PARCOURS);
	}

	// -----------------Missile--------------------

	public void deplacerMissile() {
		for (Missile missile : this.missilesVaisseau) {
			missile.deplacerVerticalementVers(Direction.BAS);
		}
		for (Missile missile : this.missilesEnvahisseur) {
			missile.deplacerVerticalementVers(Direction.HAUT);
		}
	}

	public void tirerMissileVaisseau(Dimension dimensionMissile, int vitesseMissile) {
		if ((this.vaisseau.dimension.hauteur() + dimensionMissile.hauteur()) > this.hauteur)
			throw new MissileException(
					"Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");
		if (System.currentTimeMillis() / 10 - this.tpsEntreDeuxMissilesVaisseau > 10
				|| this.tpsEntreDeuxMissilesVaisseau == 0) {
			this.missilesVaisseau.add(this.vaisseau.tirerUnMissile(dimensionMissile, vitesseMissile));
			tpsEntreDeuxMissilesVaisseau = System.currentTimeMillis() / 10;
		}
	}

	public void tirerMissileEnvahisseur(Dimension dimensionMissile, int vitesseMissile) {
		int rand = (int) (Math.random() * this.envahisseurs.size());
		if ((this.envahisseurs.get(rand).dimension.hauteur() + dimensionMissile.hauteur()) < 0)
			throw new MissileException(
					"Pas assez de hauteur libre entre l'envahisseur et le bas de l'espace jeu pour tirer le missile");
		if (System.currentTimeMillis() / 10 - this.tpsEntreDeuxMissilesEnvahisseur > 15
				|| this.tpsEntreDeuxMissilesEnvahisseur == 0) {
			this.missilesEnvahisseur.add(this.envahisseurs.get(rand).tirerUnMissile(dimensionMissile, vitesseMissile));
			this.tpsEntreDeuxMissilesEnvahisseur = System.currentTimeMillis() / 10;
		}
	}

	// -----------------Jeu-------------------------

	@Override
	public void evoluer(Commande commandeUser) {

		if (this.vaisseau != null) {
			if (commandeUser.gauche)
				deplacement.deplacerSpriteVersLaGauche(this.vaisseau);

			if (commandeUser.droite)
				deplacement.deplacerSpriteVersLaDroite(this.vaisseau);

			if (commandeUser.tir)
				this.tirerMissileVaisseau(new Dimension(Constante.MISSILE_LONGUEUR, Constante.MISSILE_HAUTEUR),
						Constante.MISSILE_VITESSE);

			if (Math.random() < 0.05 && !this.envahisseurs.isEmpty())
				this.tirerMissileEnvahisseur(new Dimension(Constante.MISSILE_LONGUEUR, Constante.MISSILE_HAUTEUR), 1);

			if (!(this.missilesVaisseau.isEmpty() && this.missilesEnvahisseur.isEmpty()) && commandeUser != null) {
				this.deplacerMissile();
				gererCollisions();
			}

		for (Envahisseur envahisseur : this.envahisseurs) {
			if (envahisseur != null && commandeUser != null) 
				deplacement.deplacementEnvahisseur(envahisseur);
			}
		}

	}
	
	public Deplacement faireDeplacement() {
		return this.deplacement;
	}

	private void gererCollisions() {
		for (int i = 0; i < this.missilesVaisseau.size(); i++) {
			collisionMissileEnvahisseur(i);
			if (this.missilesVaisseau.get(i).getPosition().ordonnee()
					- this.missilesVaisseau.get(i).getDimension().hauteur() < 0)
				this.missilesVaisseau.remove(i);
		}
		collisionMissileVaisseau();
		collisionMissileMissile();
	}

	private void collisionMissileEnvahisseur(int i) {
		for (int j = 0; j < this.envahisseurs.size(); j++) {
			if (this.envahisseurs.get(j) != null
					&& collision.detecterCollision(this.missilesVaisseau.get(i), this.envahisseurs.get(j))) {
				this.envahisseurs.remove(j);
				score++;
				if (this.envahisseurs.isEmpty() && nbVague < Constante.NB_VAGUE) {
					this.genererLigneEnvahisseur(Constante.NB_ENVAHISSEUR_VAGUE_MINI, Constante.ESPACE_LONGEUR_ENTRE_ENVAHISSEURS);
				}
			}
		}
	}

	private void collisionMissileVaisseau() {
		int i = 0;
		while (i < this.missilesEnvahisseur.size() && this.vaisseau != null) {
			if (collision.detecterCollision(this.missilesEnvahisseur.get(i), this.vaisseau))
				this.vaisseau = null;

			if (this.missilesEnvahisseur.get(i).getPosition().ordonnee() > this.hauteur)
				this.missilesEnvahisseur.remove(i);

			i++;
		}
	}

	private void collisionMissileMissile() {
		for (int i = 0; i < this.missilesEnvahisseur.size(); i++) {
			for (int j = 0; j < this.missilesVaisseau.size(); j++) {
				if (this.collision.detecterCollision(this.missilesEnvahisseur.get(i), this.missilesVaisseau.get(j))) {
					this.missilesEnvahisseur.remove(i);
					this.missilesVaisseau.remove(j);
					break;
				}
			}
		}
	}

	private char recupererMarqueDeLaPosition(int x, int y) {
		char marque = Constante.MARQUE_VIDE;
		if (this.aUnSpriteQuiOccupeLaPosition(x, y, this.vaisseau))
			marque = Constante.MARQUE_VAISSEAU;
		else if (!this.missilesVaisseau.isEmpty()) {
			int i = 0;
			while (i < this.missilesVaisseau.size() && marque == Constante.MARQUE_VIDE) {
				if (this.aUnSpriteQuiOccupeLaPosition(x, y, this.missilesVaisseau.get(i)))
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
		return ((this.envahisseurs.isEmpty() && nbVague == Constante.NB_VAGUE) || this.vaisseau == null);
	}

	// -----------------getters-------------

	public int getScore() {
		return this.score;
	}

	public List<Missile> recupererMissilesVaisseau() {
		return this.missilesVaisseau;
	}

	public List<Missile> recupererMissilesEnvahisseur() {
		return this.missilesEnvahisseur;
	}

	public List<Envahisseur> recupererEnvahisseurs() {
		return this.envahisseurs;
	}

	public Vaisseau recupererVaisseau() {
		return this.vaisseau;
	}

}