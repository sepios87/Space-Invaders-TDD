    package fr.unilim.iut.spaceinvaders;
  
    public class SpaceInvaders {

	    int longueur;
	    int hauteur;

	    public SpaceInvaders(int longueur, int hauteur) {
		   this.longueur = longueur;
		   this.hauteur = hauteur;
		   System.out.println("test");
	   }
	    
		@Override
		public String toString() {
			StringBuilder espaceDeJeu = new StringBuilder();
			for (int i = 0; i < hauteur; i++) {
				for (int j = 0; j < longueur; j++) {
					espaceDeJeu.append('.');
				}
				espaceDeJeu.append('\n');
			}
			return espaceDeJeu.toString();
		}

   }