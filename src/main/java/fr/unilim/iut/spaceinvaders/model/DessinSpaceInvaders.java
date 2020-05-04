package fr.unilim.iut.spaceinvaders.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import fr.unilim.iut.spaceinvaders.moteurjeu.DessinJeu;

/**
 * un afficheur graphique associe au JeuTest fourni
 * 
 * @author vthomas
 */
public class DessinSpaceInvaders implements DessinJeu {

	private SpaceInvaders jeu;

	   public DessinSpaceInvaders(SpaceInvaders spaceInvaders) {
		   this.jeu = spaceInvaders;
	   }

	   @Override
	   public void dessiner(BufferedImage im) {
		   if (this.jeu.aUnVaisseau()) {
			   Vaisseau vaisseau = this.jeu.recupererVaisseau();
			   this.dessinerUnSprite(vaisseau, im, Color.gray);
		   }
		   if (this.jeu.aUnMissile()) {
			   Missile missile = this.jeu.recupererMissile();
			   this.dessinerUnSprite(missile, im, Color.blue);
		   }
		   if (this.jeu.aUnEnvahisseur()) {
			   Envahisseur envahisseur = this.jeu.recupererEnvahisseur();
			   this.dessinerUnSprite(envahisseur, im, Color.red);
		   }
	   }
	   
	   private void dessinerUnSprite(Sprite sprite, BufferedImage im, Color couleur) {
		   Graphics2D crayon = (Graphics2D) im.getGraphics();
		   crayon.setColor(couleur);
		   crayon.fillRect(sprite.abscisseLaPlusAGauche(), sprite.ordonneeLaPlusBasse(), sprite.getDimension().longueur(), sprite.getDimension().hauteur());
	   }

}
