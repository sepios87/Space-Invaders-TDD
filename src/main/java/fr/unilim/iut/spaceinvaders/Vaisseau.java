package fr.unilim.iut.spaceinvaders;

public class Vaisseau {

	private Position origine;
	private Dimension dimension;
	private int vitesse;


	public Vaisseau(Dimension dimension, Position positionOrigine, int vitesse) {
		this.dimension = dimension;
		this.origine = positionOrigine;
		this.vitesse = vitesse;
	}
	   
	public Vaisseau(Dimension dimension, Position positionOrigine) {
		this(dimension, positionOrigine, 1);
	}
   
   public Vaisseau(Dimension dimension) {
	    this(dimension, new Position(0, 0), 1);
   }

    public boolean occupeLaPosition(int x, int y) {
	    return estAbscisseCouverte(x) && estOrdonneeCouverte(y);
    }

    private boolean estOrdonneeCouverte(int y) {
	   return (ordonneeLaPlusBasse() <= y) && (y <= ordonneeLaPlusHaute());
    }

    private boolean estAbscisseCouverte(int x) {
	   return (abscisseLaPlusAGauche() <= x) && (x <= abscisseLaPlusADroite());
    }

    public int ordonneeLaPlusBasse() {
	    return this.origine.ordonnee() - this.dimension.hauteur() + 1;
    }

    public int ordonneeLaPlusHaute() {
	   return this.origine.ordonnee();
   }

   public int abscisseLaPlusADroite() {
	   return this.origine.abscisse() + this.dimension.longueur() - 1;
   }

   public int abscisseLaPlusAGauche() {
	   return this.origine.abscisse();
   }

   public void seDeplacerVersLaDroite() {
		this.origine.changerAbscisse(this.origine.abscisse() + vitesse);
	}
   
   public void seDeplacerVersLaGauche() {
		this.origine.changerAbscisse(this.origine.abscisse() - vitesse);
	}

   public void positionner(int x, int y) {
	   this.origine.changerAbscisse(x);
	   this.origine.changerOrdonnee(y);
   }

	public Dimension getDimension() {
		return dimension;
	}

}